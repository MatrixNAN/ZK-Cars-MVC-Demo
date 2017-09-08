package zkmysqldemo;

// package zkmysqldemo;

import zkmysqldemo.Car;
import java.util.List;
import org.zkoss.bind.annotation.*;

public class SearchViewModel 
{	
	private String keyword;
	private List<Car> carList;
	private Car selectedCar;
	
	// private CarService carService = new CarServiceImpl();
	private CarService carService = new Model();
	
	public void setKeyword(String keyword) 
	{
		this.keyword = keyword;
	}
	
	public String getKeyword() 
	{
		return keyword;
	}

	public List<Car> getCarList()
	{
		return carList;
	}
	
		
	public void setSelectedCar(Car selectedCar) 
	{
		this.selectedCar = selectedCar;
	}
	
	public Car getSelectedCar() 
	{
		return selectedCar;
	}

	
	@Command
	@NotifyChange("carList")
	public void search()
	{
		carList = carService.search(keyword);
	}
}
