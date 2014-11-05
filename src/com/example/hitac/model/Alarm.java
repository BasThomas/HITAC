package com.example.hitac.model;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Alarm
{
	private ArrayList<Timestamp> times;
	
	public Alarm()
	{
		this.setTimes(new ArrayList<Timestamp>());
		this.getTimes().add(new Timestamp(0));
	}
	
	@Override
	public String toString()
	{
		String value = "";
		
		for (Timestamp t : times)
		{
			value += "- " + t.toString();
		}
		
		return value;
	}

	/**
	 * @return the times
	 */
	public ArrayList<Timestamp> getTimes()
	{
		return times;
	}

	/**
	 * @param times the times to set
	 */
	private void setTimes(ArrayList<Timestamp> times)
	{
		this.times = times;
	}
}
