package com.codebase.backend.admin.service;

import com.codebase.backend.admin.dto.Visitor;
import com.codebase.backend.admin.repository.VisitorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class VisitorService {

    @Autowired
    private VisitorRepository visitorRepository;

    public void  save(Visitor visitor) {
        if(!this.visitorRepository.isIpRecordedRecently(visitor)) {
            this.visitorRepository.saveVisitor(visitor);
            System.out.println("저장된 IP 주소 : " + visitor.getVisitIp());
        } else {
            System.out.println("중복된 IP 주소 : " + visitor.getVisitIp());
        }
    }

    public List<Map<String, Object>> getWeeklyVisitorCount() {
        return this.visitorRepository.getWeeklyVisitorCount();
    }

}

