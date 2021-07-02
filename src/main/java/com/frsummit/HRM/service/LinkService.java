package com.frsummit.HRM.service;

import java.util.List;

import com.frsummit.HRM.model.Links;

public interface LinkService {

	public void saveLink(Links links); // stl - exposed

	public List<Links> findAllLinks(); // stl - exposed
}
