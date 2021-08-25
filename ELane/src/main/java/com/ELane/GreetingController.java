package com.ELane;

/**
 * Created by User on 4/19/2017.
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(path="/demo")
public class GreetingController extends HttpServlet {
    Integer adminID = 0000;
    String ItemSelected="";
    ArrayList<MyCart>myCarts=new ArrayList<MyCart>();
    @Autowired
    ProducedItemRepository producedItemRepository;
    @Autowired
    private ConsumerAddressRepository consumerAddressRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private CategoryProductRepository categoryProductRepository;
    @Autowired
    private CustomerOrderRepository customerOrderRepository;
    @Autowired
    private ConsumerRepository consumerRepository;
    @Autowired
    private ProducerRepository producerRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private NotificationRepository notificationRepository;
    //@RequestMapping("/greeting")@ResponseBody

    @GetMapping(path="/addCategory")
    public String addCategory1(Model user,Model usertype,HttpSession session) {
        if((Integer)session.getAttribute("userID")==adminID){
            user.addAttribute("USERNAME",session.getAttribute("userName"));
            usertype.addAttribute("USERTYPE",session.getAttribute("usertype"));
            return "addCategory";
        }
        return "error";
    }
    @GetMapping(path="/check")
    public String check() {
        return "check";
    }
    @PostMapping(path="/addCategory") // Map ONLY GET Requests
    public @ResponseBody String addCategory (Model user,Model usertype,@ModelAttribute CategoryProduct categoryProduct,HttpSession session) {
        if (session.getAttribute("userID")==null)return "error";

        categoryProductRepository.save(categoryProduct);
        user.addAttribute("USERNAME", session.getAttribute("userName"));
        usertype.addAttribute("USERTYPE", session.getAttribute("usertype"));
        return "saved";
    }

    @GetMapping(path = "/addMyproduct")
    public  String addMyProduct_Pre(Model model,HttpSession session) {
        Integer myid = (Integer) session.getAttribute("userID");
        System.out.println("userid id = " + myid);
        List<Producer> producers = producerRepository.findById(myid);
        if(producers.isEmpty() && myid != adminID){
            return "error";
        }
        List<CategoryProduct> categoryofProducts = categoryProductRepository.findByCategoryname("food");
        List<Product> products = productRepository.findByCategoryid(categoryofProducts.get(0).getCategoryid());

        model.addAttribute("allProduct", products);
        model.addAttribute("USERNAME", session.getAttribute("userName"));
        model.addAttribute("USERTYPE", session.getAttribute("usertype"));

        return "addMyProduct";
    }
    @PostMapping(path = "/addMyproduct")
    public String addMyProduct_post(HttpSession session,Model model,@RequestParam("amount") String amount,@RequestParam ("productid") String productid ) {
        Integer myId= (Integer) session.getAttribute("userID");
        String s = "product id : "+productid+"  of ammount : "+amount;
        ProducedItem producedItem = new ProducedItem(Integer.parseInt(productid),myId,Integer.parseInt(amount));
        producedItemRepository.save(producedItem);
        System.out.println(s);
        List<CategoryProduct> categoryofProducts = categoryProductRepository.findByCategoryname("food");
        List<Product> products = productRepository.findByCategoryid(categoryofProducts.get(0).getCategoryid());

        model.addAttribute("allProduct", products);
        model.addAttribute("USERNAME",session.getAttribute("userName"));
        model.addAttribute("USERTYPE", session.getAttribute("usertype"));
        model.addAttribute("submit","submitted");
        return "addMyProduct";
    }
    @GetMapping(path = "/addProduct")
    public  String addProduct(Model user,Model usertype,Model model,HttpSession session) {
        Integer myid = (Integer) session.getAttribute("userID");
        List<Producer> producers = producerRepository.findById(myid);
        if(producers.isEmpty() && myid != adminID){
            return "error";
        }
        List<CategoryProduct> categoryofProducts = categoryProductRepository.findAll();
        for(int i=0; i<categoryofProducts.size();i++){
            System.out.println(categoryofProducts.get(i).getCategoryname());
        }
        model.addAttribute("allCategory", categoryofProducts);
        user.addAttribute("USERNAME",session.getAttribute("userName"));
        usertype.addAttribute("USERTYPE", session.getAttribute("usertype"));
        return "addProduct";
    }

    private static String UPLOADED_FOLDER = "E://Courses//3-2//SD//Project//newELane//ELane//src//main//resources//static//";
    @PostMapping(path = "/addProduct")
    public String addProduct(HttpSession session,Model model,Model user,Model usertype,@RequestParam("file") MultipartFile file,@RequestParam("amount") String amount,
                             RedirectAttributes redirectAttributes, @ModelAttribute Product product,@RequestParam("category") String category) {
        System.out.println("***************" + category);
        List<CategoryProduct> categoryofProducts= categoryProductRepository.findByCategoryname(category);
        System.out.println(categoryofProducts.size());
        product.setCategoryid(categoryofProducts.get(0).getCategoryid());
        System.out.println(categoryofProducts.get(0).getCategoryid());

        System.out.println("===========================================================1");

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "saved";
        }
        try {
            productRepository.save(product);
            System.out.println(product.getProductid());
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            String fileHeader = "/"+product.getProductid()+"_";
            Path path = Paths.get(UPLOADED_FOLDER + fileHeader + file.getOriginalFilename());
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");
            System.out.println("HEY!!!! You successfully uploaded '" + file.getOriginalFilename() + "'");
            // List<Consumer> list= consumerRepository.findAll();
            String fname=fileHeader + file.getOriginalFilename();
            // list.get(0).setImage(fname);
            // System.out.println(list.get(0).getLastname() + "  " + list.get(0).getImage() + "   " + fname);
            //  ImageConsumer consumer1=new ImageConsumer(1000,"jd","jd",fname);
            // imageConsumerRepository.save(consumer1);
            System.out.println("---------------------Before setting fname--------------------------");
            product.setImagename(fname);
            System.out.println("-----------------------after setting-------------------------------");
            productRepository.save(product);
        } catch (IOException e) {
            e.printStackTrace();
        }
        user.addAttribute("USERNAME", session.getAttribute("userName"));
        usertype.addAttribute("USERTYPE",session.getAttribute("usertype"));
        System.out.println("==================================================2");
        //return "saved";
        Integer myId= (Integer) session.getAttribute("userID");
        String s = "product id : "+product.getProductid()+"  of ammount : "+amount;
        ProducedItem producedItem = new ProducedItem(product.getProductid(),myId,Integer.parseInt(amount));
        producedItemRepository.save(producedItem);
        List<CategoryProduct> categoryProducts = categoryProductRepository.findAll();
        for(int i=0; i<categoryofProducts.size();i++){
            System.out.println(categoryofProducts.get(i).getCategoryname());
        }
        model.addAttribute("allCategory", categoryProducts);
        user.addAttribute("USERNAME", session.getAttribute("userName"));
        usertype.addAttribute("USERTYPE",session.getAttribute("usertype"));
        model.addAttribute("submit", "submitted");
        return "addProduct";

    }

    @GetMapping(path="/food")
    public String showAllFood(Model user,Model usertype,Model model,HttpSession session) {
       // if (session.getAttribute("userID")==null)return "error";
        List<CategoryProduct> list_cat=categoryProductRepository.findByCategoryname("food");
        Integer id=list_cat.get(0).getCategoryid();
        //Integer id=84;
        Iterable<Product> allProducts = productRepository.findByCategoryid(id);
        model.addAttribute("allProduct", allProducts);
        ItemSelected="food";
        user.addAttribute("USERNAME",session.getAttribute("userName"));
        usertype.addAttribute("USERTYPE",session.getAttribute("usertype"));
        return "showProduct";
    }
    @GetMapping(path="/cloths")
    public String showAllCloths(Model usertype,Model model,HttpSession session,Model user) {

//        if (session.getAttribute("userID")==null)return "error";
        List<CategoryProduct> list_cat=categoryProductRepository.findByCategoryname("cloths");
        Integer id=list_cat.get(0).getCategoryid();
        //Integer id=84;
        Iterable<Product> allProducts = productRepository.findByCategoryid(id);
        model.addAttribute("allProduct", allProducts);
        ItemSelected="cloths";
        user.addAttribute("USERNAME",session.getAttribute("userName"));
        usertype.addAttribute("USERTYPE",session.getAttribute("usertype"));
        return "showProduct";
    }
    @GetMapping("/craft")
    public String showAllCraft(Model model,HttpSession session,Model user,Model usertype) {
  //      if (session.getAttribute("userID")==null)return "error";
        List<CategoryProduct> list_cat=categoryProductRepository.findByCategoryname("craft");
        Integer id=list_cat.get(0).getCategoryid();
        //Integer id=84;
        Iterable<Product> allProducts = productRepository.findByCategoryid(id);
        model.addAttribute("allProduct", allProducts);
        ItemSelected="craft";
        user.addAttribute("USERNAME",session.getAttribute("userName"));
        usertype.addAttribute("USERTYPE",session.getAttribute("usertype"));
        return "showProduct";
    }
    @PostMapping(path="/addtobag")
    public String addtobag (HttpSession session,Model usertype,Model user,@RequestParam ("productid") String productid,Model model,@RequestParam("product_cat") String product_cat) {
        Boolean existinCart=false;
        System.out.println(productid);
        List<Product> myproduct=productRepository.findByProductid(Integer.parseInt(productid));
        for(int i=0; i<myCarts.size();i++){
            if(myCarts.get(i).getProductid()==myproduct.get(0).getProductid()){
                myCarts.get(i).setAmount(myCarts.get(i).getAmount()+1);
                existinCart=true;
                break;
            }
        }
        if(myCarts.isEmpty() || existinCart==false){
            MyCart myCart=new MyCart(myproduct.get(0).getProductid(),myproduct.get(0).getProductname(),myproduct.get(0).getPrice(),1,myproduct.get(0).getImagename());
            myCarts.add(myCart);
        }

        for(int i=0 ; i<myCarts.size(); i++){
            System.out.println(myCarts.get(i).getProductname() +"&"+myCarts.get(i).getAmount());
        }
        System.out.println("*****");
        Iterable<Product> allProducts = productRepository.findByCategoryid(Integer.parseInt(product_cat));
        model.addAttribute("add","added");
        model.addAttribute("allProduct", allProducts);
        user.addAttribute("USERNAME",session.getAttribute("userName"));
        usertype.addAttribute("USERTYPE",session.getAttribute("usertype"));
        return "showProduct";
    }

    @RequestMapping("/see_mycart")
    public String see_mycart(Model usertype,Model model,HttpSession session,Model user) {
        if (session.getAttribute("userID")==null)return "error";
        model.addAttribute("mycart", myCarts);
        user.addAttribute("USERNAME",session.getAttribute("userName"));
        usertype.addAttribute("USERTYPE",session.getAttribute("usertype"));
        List<Product> products =  productRepository.findAll();
       // Collections.reverse(products);
        ArrayList<Product> latest = new ArrayList<>();
        for(int i=products.size()-1; i>(products.size()-13);i--){
            System.out.println(products.get(i).getProductid());
            latest.add(products.get(i));
        }
        model.addAttribute("allProduct", latest);
        user.addAttribute("USERNAME",session.getAttribute("userName"));
        usertype.addAttribute("USERTYPE",session.getAttribute("usertype"));
        return "see_mycart";
    }

    @PostMapping(path="/removeFromBag")
    public String removeFromBag (@RequestParam ("productid") String productid,Model model,Model user,Model usertype, HttpSession session) {

        System.out.println(productid);
        for(int i=0;i<myCarts.size();i++){
            if(myCarts.get(i).getProductid()==Integer.parseInt(productid)){
                myCarts.remove(i);
                break;
            }
        }
        for(int i=0 ; i<myCarts.size(); i++){
            System.out.println(myCarts.get(i).getProductname());
        }


        System.out.println("*****");
        model.addAttribute("mycart", myCarts);
        List<Product> products =  productRepository.findAll();
        // Collections.reverse(products);
        ArrayList<Product> latest = new ArrayList<>();
        for(int i=products.size()-1; i>(products.size()-13);i--){
            System.out.println(products.get(i).getProductid());
            latest.add(products.get(i));
        }
        model.addAttribute("allProduct", latest);
        model.addAttribute("remove","removed");
        user.addAttribute("USERNAME",session.getAttribute("userName"));
        usertype.addAttribute("USERTYPE",session.getAttribute("usertype"));

        return "see_mycart";
    }

    @PostMapping(path="/submit_cart")
    public String submit_cart (@ModelAttribute Payment payment,@ModelAttribute Notification notification,HttpSession session,Model user,Model usertype,Model model) {
        System.out.println(session.getAttribute("userID"));
        Integer ss= (Integer) session.getAttribute("userID");
        System.out.println(ss);
        payment.setcustomerid(ss);
        payment.setIsdue(true);
        paymentRepository.save(payment);
        double total_price=0;
        for(int i=0;i<myCarts.size();i++){
            total_price += myCarts.get(i).getAmount() * Double.parseDouble(myCarts.get(i).getPrice());
        }
        CustomerOrder customerOrder=new CustomerOrder(payment.getID(),total_price);
        customerOrder.setMydate(new Date());
        customerOrderRepository.save(customerOrder);

        for(int i=0; i<myCarts.size();i++){
            CartItem cartItem= new CartItem(customerOrder.getid(),myCarts.get(i).getProductid(),myCarts.get(i).getAmount());
            cartItemRepository.save(cartItem);
        }
        ArrayList<Notification> notifications = new ArrayList<>();
        for(int i=0;i<myCarts.size();i++){
            Integer myid = (Integer) session.getAttribute("userID");
            List<ProducedItem> producedItems = producedItemRepository.findByProductid(myCarts.get(i).getProductid());
            List<Product> plist = productRepository.findByProductid(myCarts.get(i).getProductid());
            notification.setAll(myid, producedItems.get(0).getProducerid(), myCarts.get(i).getProductid(),myCarts.get(i).getAmount(),plist.get(0).getProductname(),plist.get(0).getImagename(),(String)session.getAttribute("userName"));
            notificationRepository.save(notification);
        }
        myCarts.clear();
        List<Product> products =  productRepository.findAll();
        // Collections.reverse(products);
        ArrayList<Product> latest = new ArrayList<>();
        for(int i=products.size()-1; i>(products.size()-13);i--){
            System.out.println(products.get(i).getProductid());
            latest.add(products.get(i));
        }
        model.addAttribute("submit","submitted");
        model.addAttribute("mycart", myCarts);
        model.addAttribute("allProduct", latest);
        user.addAttribute("USERNAME",session.getAttribute("userName"));
        usertype.addAttribute("USERTYPE",session.getAttribute("usertype"));
        return "see_mycart";
    }
    @GetMapping(path="/logout")
    public String logout (Model user,Model usertype, HttpSession session) {
        session.invalidate();
        myCarts.clear();
        return "Home";
    }

}