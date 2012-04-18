/**
 * Copyright 2011 The Kuali Foundation Licensed under the
 * Educational Community License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package org.kuali.mobility.file.service;

import java.util.List;

import org.kuali.mobility.file.dao.FileDao;
import org.kuali.mobility.file.entity.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FileServiceImpl implements FileService {

	private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(FileServiceImpl.class);		
	private String ME = this.getClass().getName();
	
	@Autowired
	private FileDao fileDao;
	
	@Override
	@Transactional
	public Long saveFile(File file){
		return fileDao.saveFile(file);
	}
	
	@Transactional
	public File findFileById(Long Id){
		return fileDao.findFileById(Id);
	}
	
	@Transactional
	public List<File> findFilesByName(String name){
		return fileDao.findFilesByName(name);
	}

	@Transactional
	public List<File> findAllFiles(){
		return fileDao.findAllFiles();
	}
	
	@Autowired
	private FileDao dao;
	public void setFileDao(FileDao dao){
		this.dao = dao;
	}
	public FileDao getFileDao(){
		return dao;
	}
	
	
}
