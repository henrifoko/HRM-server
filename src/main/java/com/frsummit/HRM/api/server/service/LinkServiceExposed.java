package com.frsummit.HRM.api.server.service;

import org.springframework.stereotype.Service;

import com.frsummit.HRM.api.server.entity.Links;
import com.frsummit.HRM.api.server.util.ModelConverter;
import com.frsummit.HRM.service_impl.LinkServiceImpl;

@Service("linkServiceExposed")
public class LinkServiceExposed extends LinkServiceImpl {

	public void saveLink(Links links) {
		super.saveLink(ModelConverter.getLocalVersion(links));
	}
}
