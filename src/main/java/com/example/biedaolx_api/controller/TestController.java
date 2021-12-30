package com.example.biedaolx_api.controller;

import com.example.biedaolx_api.entity.Test;
import com.example.biedaolx_api.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin("**")
public class TestController {

    private final TestService testService;


    //http://localhost:8080/api/test?page=0
    @GetMapping("/test")
    public List<Test> getTests(@RequestParam Integer page){
        //setting defaults for null and negative pages
        int pageNumber = page != null && page>0 ? page:0;
        return testService.getTests(pageNumber);

    }
    @GetMapping("/test/{id}")
    public Test getTest(@PathVariable("id") Integer id){
        return testService.getTest(id);
    }

    @PostMapping("/addTest")
    public Test addTest(@RequestBody Test test){
         return testService.addTest(test);
    }


    @PutMapping("editTest")
    public Test editTest(@RequestBody Test test){
        return testService.editTest(test);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTest(@PathVariable int id){
        testService.deleteTest(id);
    }

}
