package kpi.model.entities;

public class Flat {

	private Integer number;
	private Double square;
	private Integer floor;
	private Integer totalRooms;
	private String type;
	private Integer serviceLife;

	public Flat() {
	}

	public Flat(Integer number, Double square, Integer floor, Integer totalRooms, String type, Integer serviceLife) {
		this.number = number;
		this.square = square;
		this.floor = floor;
		this.totalRooms = totalRooms;
		this.type = type;
		this.serviceLife = serviceLife;
	}

	public Integer getNumber() {
		return number;
	}

	public Flat setNumber(Integer number) {
		this.number = number;
		return this;
	}

	public Double getSquare() {
		return square;
	}

	public Flat setSquare(Double square) {
		this.square = square;
		return this;
	}

	public Integer getFloor() {
		return floor;
	}

	public Flat setFloor(Integer floor) {
		this.floor = floor;
		return this;
	}

	public Integer getTotalRooms() {
		return totalRooms;
	}

	public Flat setTotalRooms(Integer totalRooms) {
		this.totalRooms = totalRooms;
		return this;
	}

	public String getType() {
		return type;
	}

	public Flat setType(String type) {
		this.type = type;
		return this;
	}

	public Integer getServiceLife() {
		return serviceLife;
	}

	public Flat setServiceLife(Integer serviceLife) {
		this.serviceLife = serviceLife;
		return this;
	}

	@Override
	public String toString() {
		return String.format("number=%10d | square=%10f | floor=%10d | totalRooms=%5d | type='%10s' | serviceLife=%5d",
			number, square, floor, totalRooms, type, serviceLife);
	}

}