package com.project.car.app.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "STORE")
public class Store implements Serializable {

	private static final long serialVersionUID = -2289407133182295632L;
	public static final String LOAD_STORE_DATA_BY_NAME = "LoadAStoresDataByName";
	public static final String LOAD_STORE_OBJECT_BY_STORE_NAME = "LOAD_STORE_OBJECT_BY_STORE_NAME";
	public static final String LOAD_ALL_STORES = "LOAD_ALL_STORES";
	public static final String LOAD_STORE_FROM_STORE_MASTER_BY_SALES = "Load_Store_From_Store_Master_By_Sales";
	public static final String LOAD_STORE_BY_SEASON = "LOAD_STORE_BY_SEASON";
	public static final String LOAD_STORE_BY_CHANNEL_ID = "LOAD_STORE_BY_CHANNEL_ID";
	public static final String LOAD_STORES_BY_STORE_NAMES = "LOAD_STORES_BY_STORE_NAMES";
	public static final String LOAD_ALL_STORE_BY_STORE_ID = "LOAD_ALL_STORE_BY_STORE_ID";
	public static final String LOAD_STORES_PREVIOUS_DATA_BY_CHANNEL = "LOAD_STORES_PREVIOUS_DATA_BY_CHANNEL";
	public static final String LOAD_NEW_STORES_BY_CHANNEL = "LOAD_NEW_STORES_BY_CHANNEL";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Address address;

	@Column(name = "Store_Name", nullable = false)
	private String storeName;

	@Column(name = "Store_Id")
	private String storeId;

	@Column(name = "Capacity")
	private int capacity;

	@Column(name = "Tolerance")
	private int tolerance;

	@Column(name = "MinimumCapacity")
	private int minimumCapacity;

	@Column(name = "transitTimeWeeks")
	private int transitTimeWeeks;

	@Transient
	private Store referenceStore;

	@Transient
	private String selectedRange;

	@Transient
	private String selectedCity;

	@Transient
	private Long revenue;

	@Transient
	private boolean incompletedStore;

	public Store(String storeName) {
		super();
		this.storeName = storeName;
	}

	public int getTransitTimeWeeks() {
		return transitTimeWeeks;
	}

	public Store() {
		super();
	}

	public String getSelectedRange() {
		return selectedRange;
	}

	public void setSelectedRange(String selectedRange) {
		this.selectedRange = selectedRange;
	}

	public String getSelectedCity() {
		return selectedCity;
	}

	public void setSelectedCity(String selectedCity) {
		this.selectedCity = selectedCity;
	}

	public void setTransitTimeWeeks(int transitTimeWeeks) {
		this.transitTimeWeeks = transitTimeWeeks;
	}

	@Transient
	private boolean visibility;

	public int getTolerance() {
		return tolerance;
	}

	public void setTolerance(int tolerance) {
		this.tolerance = tolerance;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Channel channel;

	@ManyToOne(targetEntity = StoreDescription.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	StoreDescription storeDescription;

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public Long getRevenue() {
		return revenue;
	}

	public void setRevenue(Long revenue) {
		this.revenue = revenue;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public StoreDescription getStoreDescription() {
		return storeDescription;
	}

	public void setStoreDescription(StoreDescription storeDescription) {
		this.storeDescription = storeDescription;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public boolean isVisibity() {
		return visibility;
	}

	public void setVisibity(boolean visibity) {
		this.visibility = visibity;
	}

	public int getMinimumCapacity() {
		return minimumCapacity;
	}

	public boolean isIncompletedStore() {
		return incompletedStore;
	}

	public void setIncompletedStore(boolean incompletedStore) {
		this.incompletedStore = incompletedStore;
	}

	public Store getReferenceStore() {
		return referenceStore;
	}

	public void setReferenceStore(Store referenceStore) {
		this.referenceStore = referenceStore;
	}

	public void setMinimumCapacity(int minimumCapacity) {
		this.minimumCapacity = minimumCapacity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 17;
		result = prime + result + ((storeName == null) ? 0 : storeName.hashCode());
		return result;
	}

}
