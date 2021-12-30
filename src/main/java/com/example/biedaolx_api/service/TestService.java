package com.example.biedaolx_api.service;

import com.example.biedaolx_api.entity.Test;
import com.example.biedaolx_api.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestRepository testRepository;

    public List<Test> getTests(int page){
        return testRepository.findAllTest(PageRequest.of( page,2));
    }

    public Test getTest(int id){
        return testRepository.lel(id);
    }


    public Test addTest(Test test) {

        if(testRepository.findById(test.getId()).isPresent()){
            throw new IllegalIdentifierException("This test is already in the database");
        }else {
            return testRepository.save(test);
        }
    }

    @Transactional
    public Test editTest(Test test) {
        Test editedTest = testRepository.findById(test.getId()).orElseThrow();
        editedTest.setValue(test.getValue());
        return editedTest;
    }

    public void deleteTest(int id) {
        testRepository.delete(testRepository.findById(id).orElseThrow());
    }
}
