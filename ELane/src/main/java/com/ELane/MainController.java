package com.ELane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by ASUS on 17-Apr-17.
 */
@Controller
@RequestMapping(path="/demo")
public class MainController {
    Integer adminID = 0000;
    @Autowired
    private ConsumerRepository consumerRepository;
    @Autowired
    private ProducerRepository producerRepository;
    @Autowired
    private ProducerAddressRepository producerAddressRepository;
    @Autowired
    private ConsumerAddressRepository consumerAddressRepository;
    @Autowired
    private CategoryProductRepository categoryProductRepository;
    @Autowired
    private FarmAddressRepository farmAddressRepository;
    @Autowired
    private NotificationRepository notificationRepository;
    @GetMapping(path="/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping(path="/signup")
    public String addConsumer (Model model,@RequestParam("username")String username,@ModelAttribute Consumer consumer,@ModelAttribute ConsumerAddress consumerAddress) {
        List<Consumer> consumers = consumerRepository.findByUsername(username);
        if (consumers.isEmpty()==false){
            model.addAttribute("uname","not");
            return "signup";
        }
        consumerRepository.save(consumer);
        Integer consumerid=consumer.getId();
        System.out.println(consumerid);
        consumerAddress.setconsumerid(consumerid);
        consumerAddressRepository.save(consumerAddress);
        //consumerAddress.setconsumerid(consumerid);
        return "login";
    }

    @GetMapping(path="/signup_for_producer")
    public String signup_for_producer() {
        return "signup_for_producer";
    }

    @PostMapping(path = "/signup_for_producer")
    public String AfterloginProd(Model model,@RequestParam("username")String username,@ModelAttribute Producer producer,@ModelAttribute ProducerAddress producerAddress,@ModelAttribute FarmAddress farmAddress,@RequestParam("hider")String fadd) {
        List<Producer> producers = producerRepository.findByUsername(username);
        if (producers.isEmpty()==false){
            model.addAttribute("uname","not");
            return "signup_for_producer";
        }
        producerRepository.save(producer);
        Integer producer_id=producer.getId();
        producerAddressRepository.save(producerAddress);
        producerAddress.setproducerid(producer_id);
        producerAddress.setPkey(producerAddress.getProducerAddressID());
        producerAddressRepository.save(producerAddress);
        System.out.println(fadd);
        farmAddressRepository.save(farmAddress);
        if(fadd.equals("yes")){
            System.out.println(fadd);
            farmAddress.setFromP_address(producerAddress);
        }
        farmAddress.setProducerid(producer_id);
        farmAddressRepository.save(farmAddress);

        return "login";
    }

    @GetMapping(path="/login")
    public String prevlogin () {
        return "login";
    }

    @PostMapping(path="/login")
    public String login (Model model,@RequestParam("username") String username, @RequestParam("password") String password,HttpSession session) {
        if(username.equals("admin") && password.equals("admin") ){
            session.setAttribute("userID", adminID);
            session.setAttribute("userName", username);
            session.setAttribute("usertype","admin");
            System.out.println("admin");
            model.addAttribute("USERNAME", session.getAttribute("userName"));
            model.addAttribute("USERTYPE", session.getAttribute("usertype"));
            return "Home";
        }
        List<Consumer> consumerList = consumerRepository.findByUsernameAndPassword(username, password);
        if (consumerList.isEmpty()) {
            List<Producer> producerList = producerRepository.findByUsernameAndPassword(username, password);
            if (producerList.isEmpty()) return "login";
            else {
                List<Notification> notifications = notificationRepository.findByToid(producerList.get(0).getId());
                session.setAttribute("userID", producerList.get(0).getId());
                System.out.println(session.getAttribute("userID"));
                session.setAttribute("userName", username);
                session.setAttribute("usertype", "producer");
                session.setAttribute("notification", notifications);
                model.addAttribute("USERNAME", session.getAttribute("userName"));
                model.addAttribute("USERTYPE", session.getAttribute("usertype"));
                model.addAttribute("notification",session.getAttribute("notification"));
                System.out.println("----------------------------------------------notification--------------------------------------------");
                System.out.println(session.getAttribute("notification"));
                return "Home";
            }
        }
        else {
            List<Notification> notifications = notificationRepository.findByToid(consumerList.get(0).getId());
            session.setAttribute("userID", consumerList.get(0).getId());
            System.out.println(session.getAttribute("userID"));
            session.setAttribute("userName", username);
            session.setAttribute("usertype","customer");
            session.setAttribute("notification", notifications);
            model.addAttribute("USERNAME", session.getAttribute("userName"));
            model.addAttribute("USERTYPE", session.getAttribute("usertype"));
            model.addAttribute("notification", session.getAttribute("notification"));
            System.out.println("----------------------------------------------notification--------------------------------------------");
            System.out.println(session.getAttribute("notification"));
            return "Home";
        }
    }
    @GetMapping(path="/Home")
    public String Home (Model category,Model user,Model usertype, HttpSession session) {
        if(session.getAttribute("userName")==null){
            //user.addAttribute("USERNAME", "0");
            return "Home";
        }
        List<CategoryProduct>categoryProducts=categoryProductRepository.findAll();
        user.addAttribute("USERNAME", session.getAttribute("userName"));
        usertype.addAttribute("USERTYPE",session.getAttribute("usertype"));
        category.addAttribute("category",categoryProducts);
        return "Home";
    }

}
