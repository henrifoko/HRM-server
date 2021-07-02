package com.frsummit.HRM.api.rmi.service;

import org.springframework.stereotype.Service;

import com.frsummit.HRM.api.rmi.model.Links;
import com.frsummit.HRM.service_impl.LinkServiceImpl;

@Service("linkServiceExposed")
public class LinkServiceExposed extends LinkServiceImpl {

	public void saveLink(Links links) {
		super.saveLink(links.toLocalModel());
	}
}
