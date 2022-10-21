package rikkei.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import rikkei.academy.model.Customer;
import rikkei.academy.model.CustomerForm;
import rikkei.academy.service.customer.ICustomerService;

import java.io.File;
import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    private ICustomerService customerService;
    @GetMapping("/")
    public String list(Model model){
        model.addAttribute("formList",customerService.findAll());
        return "/customer/list";
    }

    @GetMapping("/create-customer")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/customer/create");
        modelAndView.addObject("customer",new Customer());
        return modelAndView;
    }
    @PostMapping("create-customer")
    public ModelAndView saveCustomer(@ModelAttribute("customer")CustomerForm customer){
        MultipartFile multipartFile = customer.getAvatar();
        String nameAvatar = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(customer.getAvatar().getBytes(), new File(fileUpload +nameAvatar));
        }catch (Exception e){
            e.printStackTrace();
        }
        Customer customer1 = new Customer(customer.getId(),customer.getFirstName(),customer.getLastName(),nameAvatar);
        customerService.save(customer1);
        ModelAndView modelAndView = new ModelAndView("/customer/create");
        modelAndView.addObject("customer", new Customer());
        modelAndView.addObject("message","New customer created success!!");
        return modelAndView;
    }
    @Value("${chinh}")
    private String fileUpload;
    @GetMapping("/edit-customer/{id}")
    public ModelAndView showFormEdit(@PathVariable Long id){
      Customer customer = customerService.findById(id);
        if (customer != null) {
            ModelAndView modelAndView = new ModelAndView("/customer/edit");
            CustomerForm customerForm = new CustomerForm(customer.getId(),customer.getFirstName(),customer.getLastName(),null);
            modelAndView.addObject("customerForm", customerForm);
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/customer/error404");
            return modelAndView;
        }

    }
    @PostMapping("/edit-customer")
    public ModelAndView editCustomer( CustomerForm customerForm){
        Customer customer = customerService.findById(customerForm.getId());
        String oldAvatar = customer.getAvatar();
        MultipartFile multipartFile = customerForm.getAvatar();
        String nameNewAvatar = multipartFile.getOriginalFilename();
//
        if (customerForm.getAvatar().isEmpty()){
            customer.setAvatar(oldAvatar);

        }else {
            customer.setAvatar(nameNewAvatar);
        }
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("/customer/edit");
        modelAndView.addObject("customerForm",customerForm);
        modelAndView.addObject("message","customer update success !!!");
        return modelAndView;
    }
    @GetMapping("/delete-customer/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        Customer customer = customerService.findById(id);
        if (customer != null){
            ModelAndView modelAndView = new ModelAndView("/customer/delete");
            modelAndView.addObject("customer",customer);
            return modelAndView;
        }else {
            ModelAndView modelAndView = new ModelAndView("/customer/erorr404");
            return modelAndView;
        }
    }
    @PostMapping("/delete-customer")
    public String deleteCustomer(@ModelAttribute("customer")Customer customer){
        customerService.remove(customer.getId());
        return "redirect:/";
    }
    @GetMapping("/detail-customer/{id}")
    public String showDetailForm(@PathVariable("id")Long id,Model model){
        Customer customer = customerService.findById(id);
        model.addAttribute("customer",customer);
        return "/customer/detail";
    }
    @GetMapping("/search")
    public String search(Model model, @RequestParam String search){
        List<Customer> customerList = customerService.search(search);
        model.addAttribute("formList",customerList);
        model.addAttribute("search",search);
        return "/customer/list";
    }



}
