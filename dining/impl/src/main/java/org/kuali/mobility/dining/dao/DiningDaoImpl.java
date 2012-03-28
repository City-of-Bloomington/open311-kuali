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

package org.kuali.mobility.dining.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.kuali.mobility.dining.dao.DiningDao;
import org.kuali.mobility.dining.entity.Place;
import org.springframework.stereotype.Repository;

@Repository
public class DiningDaoImpl implements DiningDao {

    @PersistenceContext
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

	@Override
	public List<Place> getPlaceList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMenusJson(String name, String location) {
		// TODO Auto-generated method stub
		return null;
	}

} 
