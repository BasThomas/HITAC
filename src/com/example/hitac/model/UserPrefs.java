package com.example.hitac.model;

import java.util.ArrayList;
import java.util.Locale;


public class UserPrefs
{
	private int sleepHours;
	private int prepTime;
	private ArrayList<Place> places;
	
	public UserPrefs()
	{
		this.setSleepHours(7);
		this.setPrepTime(60);
		this.setPlaces(new ArrayList<Place>());
		
		this.getPlaces().add(new Place("Rachelsmolen 1", "Fontys Hogeschool"));
	}
	
	public UserPrefs(int sleepHours, int prepTime, String address, String name)
	{
		this.setSleepHours(sleepHours);
		this.setPrepTime(prepTime);
		this.setPlaces(new ArrayList<Place>());
		
		this.getPlaces().add(new Place(address, name));
		
		// https://maps.googleapis.com/maps/api/distancematrix/json?origins=51.4516268,5.4814267&destinations=51.2819175,5.7450811&language=nl
	}
	
	@Override
	public String toString()
	{
		Locale current = Locale.getDefault();
		String value = String.format(current,
				"Hours of sleep: %d, preparation time: %d minutes",
				this.getSleepHours(), this.getPrepTime());
		
		for (Place p : getPlaces())
		{
			value += "\n- " + p.toString();
		}
		
		return value;
	}
	
	public boolean updateUserPrefs(int sleepHours, int prepTime)
	{
		int[] checkInput = {sleepHours, prepTime};
		
		for (int c : checkInput)
		{
			if (c == 0)
			{
				System.out.println("Time must not be 0.");
				return false;
			}
		}
		
		this.setSleepHours(sleepHours);
		this.setPrepTime(prepTime);
		
		return true;
	}

	/**
	 * @return the prepTime
	 */
	public int getPrepTime()
	{
		return prepTime;
	}

	/**
	 * @param prepTime the prepTime to set
	 */
	private void setPrepTime(int prepTime)
	{
		this.prepTime = prepTime;
	}

	/**
	 * @return the sleepHours
	 */
	public int getSleepHours()
	{
		return sleepHours;
	}

	/**
	 * @param sleepHours the sleepHours to set
	 */
	private void setSleepHours(int sleepHours)
	{
		this.sleepHours = sleepHours;
	}

	/**
	 * @return the places
	 */
	public ArrayList<Place> getPlaces()
	{
		return places;
	}

	/**
	 * @param places the places to set
	 */
	private void setPlaces(ArrayList<Place> places)
	{
		this.places = places;
	}
}
