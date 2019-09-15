package com.project.car.app.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "STORE_DESCRIPTION")

public class StoreDescription implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String LOAD_STORE_DESCRIPTION = "LoadStoreDescription";
	public static final String LOAD_STORE_DESCRIPTION_BY_NAME = "LoadStoreDescriptionByName";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "Master_Name")
	private String storeMasterName;

	@Column(name = "Description")
	private String description;

	@Column(name = "Updated_TS")
	private Timestamp updatedTs;
	@JsonIgnore
	@OneToMany(targetEntity = Store.class, mappedBy = "storeDescription", cascade = {
			CascadeType.ALL }, orphanRemoval = true)
	private Set<Store> storeMaster = new HashSet<Store>();

	public String getStoreMasterName() {
		return storeMasterName;
	}

	public void setStoreMasterName(String storeMasterName) {
		this.storeMasterName = storeMasterName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getUpdatedTs() {
		return updatedTs;
	}

	public void setUpdatedTs(Timestamp updatedTs) {
		this.updatedTs = updatedTs;
	}

	public Set<Store> getStoreMaster() {
		return storeMaster;
	}

	public void setStoreMaster(Set<Store> storeMaster) {
		this.storeMaster = storeMaster;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
