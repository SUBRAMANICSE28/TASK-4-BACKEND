package com.iktpreobuka.jobster.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "country_regions")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class CountryRegionEntity {

	private static final Integer STATUS_INACTIVE = 0;
	private static final Integer STATUS_ACTIVE = 1;
	private static final Integer STATUS_ARCHIVED = -1;

	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "country")
	@NotNull (message = "Country must be provided.")
	private CountryEntity country;
	
	@JsonIgnore
	@OneToMany(mappedBy = "region", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH})
	private List<CityEntity> cities = new ArrayList<>();


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	//@JsonView(Views.Parent.class)
	protected Integer id;
	
	//@JsonView(Views.Student.class)
	@Pattern(regexp = "^[a-zA-Z]{2,}+( [a-zA-Z_]+)*$", message="Country region name is not valid.")
	protected String countryRegionName;
	
	@Pattern(regexp = "^[a-zA-Z]{2,}+( [a-zA-Z_]+)*$", message="Country name is not valid.")
	private String countryName;
	
	//@JsonView(Views.Admin.class)
	@Max(1)
    @Min(-1)
    @Column(name = "status", nullable = false)
	private Integer status;
	
	//@JsonView(Views.Admin.class)
	private Integer createdById;
	
    //@JsonView(Views.Admin.class)
    private Integer updatedById;
	
    @JsonIgnore
	@Version
	private Integer version;

	public CountryRegionEntity() {
		super();
	}
	

	public CountryRegionEntity(@NotNull(message = "Country must be provided.") CountryEntity country,
			@Pattern(regexp = "^[a-zA-Z]{2,}+( [a-zA-Z_]+)*$", message = "Country region name is not valid.") String countryRegionName,
			Integer createdById) {
		super();
		this.country = country;
		this.countryRegionName = countryRegionName;
		this.status = getStatusActive();
		this.createdById = createdById;
	}

	public CountryRegionEntity(
			@Pattern(regexp = "^[a-zA-Z]{2,}+( [a-zA-Z_]+)*$", message = "Country name is not valid.") @NotNull(message = "Country must be provided.") String countryName,
			@Pattern(regexp = "^[a-zA-Z]{2,}+( [a-zA-Z_]+)*$", message = "Country region name is not valid.") String countryRegionName) {
		super();
		this.countryName = countryName;
		this.countryRegionName = countryRegionName;
		this.status = getStatusActive();
	}
	
	public CountryRegionEntity(@NotNull(message = "Country must be provided.") CountryEntity country,
			@Pattern(regexp = "^[a-zA-Z]{2,}+( [a-zA-Z_]+)*$", message = "Country region name is not valid.") String countryRegionName) {
		super();
		this.country = country;
		this.countryRegionName = countryRegionName;
		this.status = getStatusActive();
	}


	public List<CityEntity> getCities() {
		return cities;
	}

	public void setCities(List<CityEntity> cities) {
		this.cities = cities;
	}

	public CountryEntity getCountry() {
		return country;
	}

	public void setCountry(CountryEntity country) {
		this.country = country;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCountryRegionName() {
		return countryRegionName;
	}

	public void setCountryRegionName(String countryRegionName) {
		this.countryRegionName = countryRegionName;
	}

	public String getCountryName() {
		return countryName;
	}


	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}


	public Integer getCreatedById() {
		return createdById;
	}

	public void setCreatedById(Integer createdById) {
		this.createdById = createdById;
	}

	public Integer getUpdatedById() {
		return updatedById;
	}

	public void setUpdatedById(Integer updatedById) {
		this.updatedById = updatedById;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}


	public void setStatusInactive() {
		this.status = getStatusInactive();
	}

	public void setStatusActive() {
		this.status = getStatusActive();
	}

	public void setStatusArchived() {
		this.status = getStatusArchived();
	}
	
	public static Integer getStatusInactive() {
		return STATUS_INACTIVE;
	}

	public static Integer getStatusActive() {
		return STATUS_ACTIVE;
	}

	public static Integer getStatusArchived() {
		return STATUS_ARCHIVED;
	}

}
