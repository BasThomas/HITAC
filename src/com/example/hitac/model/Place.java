package com.example.hitac.model;

import java.util.Locale;

public class Place
{
	private String address;
	private String name;
	private String longitude = null;
	private String latitude = null;
	
	public Place(String address, String name)
	{
		this.setAddress(address);
		this.setName(name);
	}
	
	@Override
	public String toString()
	{
		Locale current = Locale.getDefault();
		String value = String.format(current, "%s", this.getAddress());
		return value;
	}
	
	public boolean updatePlace(String address, String name)
	{
		String[] checkInput = {address, name};
		
		if (this.getAddress() == address && this.getName() == name)
		{
			// Place still the same.
			
			return false;
		}
		
		if (isValid(checkInput))
		{
			this.setAddress(address);
			this.setName(name);
			
			this.setLatitude(null);
			this.setLongitude(null);
			
			return true;
		}
		
		return false;
	}
	
	public boolean isValid(String[] check)
	{
		for (String c : check)
		{
			if (c == "" || c == null)
			{
				System.out.println("Input invalid (null or empty)");
				return false;
			}
		}
		
		return true;
	}

	/**
	 * @return the address
	 */
	public String getAddress()
	{
		return address;
	}

	/**
	 * @param address the address to set
	 */
	private void setAddress(String address)
	{
		this.address = address;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name the name to set
	 */
	private void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return the longitude
	 */
	public String getLongitude()
	{
		return longitude;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(String longitude)
	{
		this.longitude = longitude;
	}

	/**
	 * @return the latitude
	 */
	public String getLatitude()
	{
		return latitude;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(String latitude)
	{
		this.latitude = latitude;
	}
}
