package com.codebase.backend.project.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.codebase.backend.project.dto.Payment;
import com.codebase.backend.project.dto.ProjectOrder;
import com.codebase.backend.project.mapper.PaymentMapper;
import com.codebase.backend.project.mapper.ProjectMapper;
import com.codebase.backend.project.mapper.ProjectOrderMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {

	private final PaymentMapper paymentMapper;
	private final ProjectOrderMapper projectOrderMapper;
	private final ProjectMapper projectMapper;
	
	public void save(Payment p) {
		
		projectMapper.soldout(p.getProject_id());
		
		p.setPurchaseTime(LocalDateTime.now());
		paymentMapper.save(p);
		
		ProjectOrder po = new ProjectOrder();
		po.setPayment_id(p.getId());
		po.setProject_id(p.getProject_id());
		po.setBuyer_id(p.getBuyer_id());
		
		projectOrderMapper.save(po);

		
	}
	
	
}
