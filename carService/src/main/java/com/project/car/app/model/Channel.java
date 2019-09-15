package com.project.car.app.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "CHANNEL")
@NamedQueries({
		@NamedQuery(name = Channel.LoAD_CHANNEL_BY_NAME, query = "select channel from Channel channel where channel.channelName =:channelName"),
		@NamedQuery(name = Channel.LOAD_CHANNELS, query = "select channel from Channel channel") })
public class Channel implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String LoAD_CHANNEL_BY_NAME = "LoAD_CHANNEL_BY_NAME";
	public static final String LOAD_CHANNELS = "LOAD_CHANNELS";
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "Channel_Name", nullable = false, length = 50)
	private String channelName;

	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Store> stores = new ArrayList<Store>();

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public List<Store> getStores() {
		return stores;
	}

	public void setStores(List<Store> stores) {
		this.stores = stores;
	}

	@Override
	public String toString() {
		return "Channel [channelName=" + channelName + ", getUuid()=" + ", getId()=" + getId() + "]";
	}

	private long getId() {
		return id;
	}

	public void setId(long id) {

		this.id = id;
	}

}
