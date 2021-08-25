package com.ELane;

/**
 * Created by User on 4/19/2017.
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(path="/demo")
public class MyprofileController extends HttpServlet {
    Integer adminID = 0000;
    ArrayList<MyCart> myCarts = new ArrayList<MyCart>();
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private CategoryProductRepository categoryProductRepository;
    @Autowired
    private CustomerOrderRepository customerOrderRepository;
    @Autowired
    private ConsumerRepository consumerRepository;
    @Autowired
    private FarmAddressRepository farmAddressRepository;
    @Autowired
    private ConsumerAddressRepository consumerAddressRepository;
    @Autowired
    private ProducerRepository producerRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private ProducerAddressRepository producerAddressRepository;
    @Autowired
    private ProducedItemRepository producedItemRepository;
    @GetMapping("/myprofile")
    public String myprofile(Model model, HttpSession session) {
        if (session.getAttribute("userID") == null) return "error";
        int id = (int) session.getAttribute("userID");
        System.out.println("my profile : " + id);
        List<Consumer> consumers = consumerRepository.findById(id);
        if (id == adminID) {
            return "admin";
        } else if (consumers.isEmpty()) {
            System.out.println("------------------------in producer section");
            List<Producer> producers = producerRepository.findById(id);
            System.out.println("------------------------producer id : " + producers.get(0).getId() + "  and name : " + producers.get(0).getUsername());
            List<ProducerAddress> producerAddresses = producerAddressRepository.findByPkey(id);
            System.out.println("------------------------producer address : " + producerAddresses.get(0).getCity());
            List<FarmAddress> farmAddresses = farmAddressRepository.findByProducerid(id);
            model.addAttribute("producer", producers.get(0));
            model.addAttribute("address", producerAddresses.get(0));
            model.addAttribute("farm",farmAddresses.get(0));
            List<ProducedItem> producedItems = producedItemRepository.findByProducerid(id);
            System.out.println("total produced item : "+producedItems.size());
            ArrayList<CartItem> cart = new ArrayList<>();
            for( int i = 0 ; i < producedItems.size() ; i++ ){
                List<CartItem> cartItems = cartItemRepository.findByProductid(producedItems.get(i).getProductid());
                for (int j = 0; j< cartItems.size();j++){
                    cart.add(cartItems.get(j));
                }
            }
            ArrayList<ProducerChart> producerSupplyCharts = new ArrayList<>();
            for (int i=0; i<cart.size();i++){
                ProducerChart supplyChart = new ProducerChart();
                List<Product> products = productRepository.findByProductid(cart.get(i).productid);
                List<CustomerOrder> customerOrders = customerOrderRepository.findById(cart.get(i).orderid);
                List<Payment> payments = paymentRepository.findById(customerOrders.get(0).getpaymentid());
                List<Consumer> consumers1 = consumerRepository.findById(payments.get(0).getcustomerid());
                List<ConsumerAddress> consumerAddresses = consumerAddressRepository.findByConsumerid(consumers1.get(0).getId());
                supplyChart.setProfile(products.get(0),cart.get(0),consumers1.get(0),payments.get(0),customerOrders.get(0),consumerAddresses.get(0));
                producerSupplyCharts.add(supplyChart);
            }
            if(producerSupplyCharts.isEmpty()) model.addAttribute("allOrders","0");
            else{
                model.addAttribute("allOrders",producerSupplyCharts);
            }
            System.out.println("---------------------------------------chart------------------------------------------");
            for (int i=0; i<producerSupplyCharts.size();i++)
            {
                producerSupplyCharts.get(i).Print();
            }
            List<Product> products = productRepository.findAll();
            ArrayList<Product> myproducts = new ArrayList<>();
            for (int i=0; i<producedItems.size();i++){
                for(int j=0; j<products.size();j++){
                    if(products.get(j).getProductid()==producedItems.get(i).getProductid()){
                        myproducts.add(products.get(j));
                    }
                }
            }
            model.addAttribute("USERNAME", session.getAttribute("userName"));
            model.addAttribute("USERTYPE", session.getAttribute("usertype"));
            model.addAttribute("ITEM",producedItems);
            model.addAttribute("product",products);
            return "producerProfile";
        } else {
            List<ConsumerAddress> consumerAddresses = consumerAddressRepository.findByConsumerid(id);
            model.addAttribute("customer", consumers.get(0));
            model.addAttribute("address", consumerAddresses.get(0));
            List<Payment> payments = paymentRepository.findByCustomerid(id);
            if (payments.isEmpty()) {
                model.addAttribute("payment", 0);
                model.addAttribute("USERNAME", session.getAttribute("userName"));
                model.addAttribute("USERTYPE", session.getAttribute("usertype"));
                return "myprofile";
            } else {
                System.out.println("payment size: " + payments.size() + "payment: " + payments.get(0).getID());
                ArrayList<CustomerOrder> orders = new ArrayList<>();
                for (int i = 0; i < payments.size(); i++) {
                    List<CustomerOrder> customerOrders = customerOrderRepository.findByPaymentid(payments.get(i).getID());
                    orders.add(customerOrders.get(0));
                }
                System.out.println("orders; " + orders.size() + " order 1 id : " + orders.get(0).getid());
                ArrayList<CartItem> carts = new ArrayList<CartItem>();
                for (int i = 0; i < orders.size(); i++) {
                    int oid = orders.get(i).getid();
                    System.out.println(oid);
                    List<CartItem> mycarts = cartItemRepository.findByOrderid(oid);
                 //   System.out.println("carts size : " + mycarts.size() + " one id : " + mycarts.get(i).getID());
                    for (int j = 0; j < mycarts.size(); j++) {
                        carts.add(mycarts.get(j));
                        System.out.println("adding in cart : " + carts.get(j).getID());
                    }
                }
                List<Product> products = productRepository.findAll();
                model.addAttribute("order", orders);
                model.addAttribute("cart", carts);
                model.addAttribute("payment", payments);
                model.addAttribute("product", products);
                model.addAttribute("USERNAME", session.getAttribute("userName"));
                model.addAttribute("USERTYPE", session.getAttribute("usertype"));
                return "myprofile";
            }
        }
    }
    @PostMapping(path="/edit_profile")
    public  String editProfile (@RequestParam ("customerid") String customerid,Model model,HttpSession session) {
        Integer cID=Integer.parseInt(customerid);
        List<Consumer> consumers = consumerRepository.findById(cID);
        List<ConsumerAddress> consumerAddresses = consumerAddressRepository.findByConsumerid(cID);
        System.out.println(consumers.get(0).getUsername());
        model.addAttribute("customer", consumers.get(0));
        model.addAttribute("address",consumerAddresses.get(0));
        model.addAttribute("USERNAME", session.getAttribute("userName"));
        model.addAttribute("USERTYPE", session.getAttribute("usertype"));
        return  "edit_profile";
    }
    @PostMapping(path = "/edited")
    public  String edited(Model model,@RequestParam("con_password")String password,@ModelAttribute Consumer consumer,@ModelAttribute ConsumerAddress consumerAddress,HttpSession session){
        Integer myid = (Integer) session.getAttribute("userID");
        System.out.println(consumer.getFirstname() + " and id is : " + myid);
        List<Consumer> consumers = consumerRepository.findById(myid);
        List<ConsumerAddress> consumerAddresses = consumerAddressRepository.findByConsumerid(myid);
        System.out.println(password);
        if(consumers.get(0).getPassword().equals(password)==false){
            model.addAttribute("customer", consumers.get(0));
            model.addAttribute("address",consumerAddresses.get(0));
            model.addAttribute("USERNAME",session.getAttribute("userName"));
            model.addAttribute("USERTYPE", session.getAttribute("usertype"));
            model.addAttribute("pass","wrong");
            return  "edit_profile";
        }
        consumers.get(0).Edit(consumer);
        consumerRepository.save(consumers.get(0));
        consumerAddresses.get(0).Edit(consumerAddress);
        consumerAddressRepository.save(consumerAddresses.get(0));
        model.addAttribute("customer", consumers.get(0));
        model.addAttribute("address",consumerAddresses.get(0));
        model.addAttribute("USERNAME",session.getAttribute("userName"));
        model.addAttribute("USERTYPE", session.getAttribute("usertype"));
        model.addAttribute("con_edit","edited");
        return  "edit_profile";
    }
    @PostMapping(path="/voucher")
    public  String voucher (Model user,Model usertype,@RequestParam ("orderid") String orderid,Model model,HttpSession session) {
        List<CartItem> cartItems = cartItemRepository.findByOrderid(Integer.parseInt(orderid));
        List<Product> allproducts = productRepository.findAll();
        ArrayList<Product> products = new ArrayList<>();
        for(int i=0; i<cartItems.size();i++){
            for (int j=0; j<allproducts.size();j++){
                if(allproducts.get(j).getProductid()==cartItems.get(i).getproductid()){
                    products.add(allproducts.get(j));
                }
            }
        }
        List<CustomerOrder> customerOrders = customerOrderRepository.findById(Integer.parseInt(orderid));
        model.addAttribute("order", customerOrders.get(0));
        model.addAttribute("carts",cartItems);
        model.addAttribute("products",products);
        user.addAttribute("USERNAME",session.getAttribute("userName"));
        usertype.addAttribute("USERTYPE", session.getAttribute("usertype"));
        return  "voucher";
    }
    @PostMapping(path="/product_details")
    public  String product_details (Model user,Model usertype,@RequestParam ("productid") String productid,Model model,HttpSession session) {
        List<Product> product = productRepository.findByProductid(Integer.parseInt(productid));
        List<CategoryProduct> categoryProducts = categoryProductRepository.findByCategoryid(product.get(0).getCategoryid());

        List<ProducedItem> producedItems = producedItemRepository.findByProductid(Integer.parseInt(productid));
        System.out.println(producedItems.size());
        System.out.println(producedItems.get(0).getProducerid());
        List<Producer> producers = producerRepository.findById(producedItems.get(0).getProducerid());
        if(producedItems.get(0).getAmount()<=0){
            System.out.println("in if and "+producedItems.get(0).getAmount());
            model.addAttribute("instock","false");
        }
        else {
            System.out.println("in else and "+producedItems.get(0).getAmount());
            model.addAttribute("instock","true");
        }
        model.addAttribute("producer",producers.get(0));
        model.addAttribute("product",product.get(0));
        model.addAttribute("producedItem", producedItems.get(0));
        model.addAttribute("category",categoryProducts.get(0).getCategoryname());
        user.addAttribute("USERNAME",session.getAttribute("userName"));
        usertype.addAttribute("USERTYPE", session.getAttribute("usertype"));
        return  "product_details";
    }
    @PostMapping(path = "/edit_profileProducer")
    public String edit_profileProducer(@RequestParam("producerid") String producerid , Model model,HttpSession session){
        List<Producer> producers = producerRepository.findById(Integer.parseInt(producerid));
        List<ProducerAddress> producerAddresses = producerAddressRepository.findByProducerid(Integer.parseInt(producerid));
        List<FarmAddress> farmAddresses = farmAddressRepository.findByProducerid(Integer.parseInt(producerid));
        model.addAttribute("producer", producers.get(0));
        model.addAttribute("address", producerAddresses.get(0));
        model.addAttribute("farm", farmAddresses.get(0));
        model.addAttribute("USERNAME",session.getAttribute("userName"));
        model.addAttribute("USERTYPE", session.getAttribute("usertype"));
        return "edit_profileProducer";
    }
    @PostMapping(path = "/after_edit_for_producer")
    public  String after_edit_for_producer(Model model,@RequestParam("con_password")String password,@ModelAttribute Producer producer,@ModelAttribute ProducerAddress producerAddress,@ModelAttribute FarmAddress farmAddress,HttpSession session){
        Integer myid = (Integer) session.getAttribute("userID");
        System.out.println(producer.getFirstname() + " and id is : " + myid);
        List<Producer> producers = producerRepository.findById(myid);
        List<ProducerAddress> producerAddresses = producerAddressRepository.findByProducerid(myid);
        List<FarmAddress> farmAddresses = farmAddressRepository.findByProducerid(myid);
        System.out.println(password);
        if(producers.get(0).getPassword().equals(password)==false){
            model.addAttribute("producer",producers.get(0));
            model.addAttribute("address", producerAddresses.get(0));
            model.addAttribute("farm", farmAddresses.get(0));
            model.addAttribute("USERNAME",session.getAttribute("userName"));
            model.addAttribute("USERTYPE", session.getAttribute("usertype"));
            model.addAttribute("pass","wrong");
            return "edit_profileProducer";
        }
        producers.get(0).Edit(producer);
        producerRepository.save(producers.get(0));
        producerAddresses.get(0).Edit(producerAddress);
        producerAddressRepository.save(producerAddresses.get(0));
        farmAddresses.get(0).Edit(farmAddress);
        farmAddressRepository.save(farmAddresses.get(0));
       // myprofile(model,session);
        model.addAttribute("producer",producers.get(0));
        model.addAttribute("address", producerAddresses.get(0));
        model.addAttribute("farm", farmAddresses.get(0));
        model.addAttribute("USERNAME",session.getAttribute("userName"));
        model.addAttribute("USERTYPE", session.getAttribute("usertype"));
        model.addAttribute("edit","edited");
        return "edit_profileProducer";
    }
}