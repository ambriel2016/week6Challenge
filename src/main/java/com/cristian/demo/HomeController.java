package com.cristian.demo;

import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    CarRepository carRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CloudinaryConfig cloudc;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("cars", carRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        return "index";
    }

    @RequestMapping("/about")
    public String about(){
        return "about";
    }

    @GetMapping("/addCar")
    public String carForm(Model model){
        model.addAttribute("car", new Car());
        return "carform";
    }

    @PostMapping("/addCar")
    public String processCar(@ModelAttribute Car car,
                              @RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "redirect:/addCar";
        }
        try {
            Map uploadResult = cloudc.upload(file.getBytes(),
                    ObjectUtils.asMap("resourcetype", "auto"));
            car.setPhoto(uploadResult.get("url").toString());
            carRepository.save(car);
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/addCar";
        }
        return "redirect:/";
    }

    @PostMapping("/processCar")
    public String processForm(@ModelAttribute Car car){
        carRepository.save(car);
        return "redirect:/";
    }

    @RequestMapping("/detailCar/{id}")
    public String showCar(@PathVariable("id") long id, Model model){
        model.addAttribute("car", carRepository.findById(id).get());
        return "show";
    }

    @RequestMapping("/updateCar/{id}")
    public String updateCar(@PathVariable("id") long id, Model model){
        model.addAttribute("car", carRepository.findById(id).get());
        return "carform";
    }

    @RequestMapping("/deleteCar/{id}")
    public String delCar(@PathVariable("id") long id){
        carRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/addCategory")
    public String categoryForm(Model model){
        model.addAttribute("category", new Category());
        return "categoryform";
    }
    @PostMapping("/processCategory")
    public String processForm(@ModelAttribute Category category){
        categoryRepository.save(category);
        return "redirect:/";
    }
    @RequestMapping("/detailCategory/{id}")
    public String showCategory(@PathVariable("id") long id, Model model){
        model.addAttribute("category", categoryRepository.findById(id).get());
        return "show";
    }

    @RequestMapping("/updateCategory/{id}")
    public String updateCategory(@PathVariable("id") long id, Model model){
        model.addAttribute("category", categoryRepository.findById(id).get());
        return "categoryform";
    }

    @RequestMapping("/deleteCategory/{id}")
    public String delCategory(@PathVariable("id") long id){
        categoryRepository.deleteById(id);
        return "redirect:/";
    }

    @RequestMapping("/search")
    public String searchPerson(@RequestParam("search") String search, Model model){
        model.addAttribute("vehiclesSearch", carRepository.findByManufacturer(search));
        model.addAttribute("categorySearch", categoryRepository.findByCategoryName(search));
        return "searchList";
    }
}

