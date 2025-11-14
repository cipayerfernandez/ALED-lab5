package es.upm.dit.aled.lab5;

import java.awt.Color;
import java.util.Objects;

import es.upm.dit.aled.lab5.gui.Position2D;

/**
 * Models an area in a hospital ER. Each Area is characterized by its name
 * (which must be unique), the time a patient must spend there to be treated (in
 * milliseconds), the capacity (maximum number of patients that can be treated
 * at the same time), the number of patients being treated at a given time, and
 * the number of patients currently waiting.
 * 
 * This class is a monitor that ensures that there is a thread safe way to enter
 * and exit the area.
 * 
 * Each Area is represented graphically by a square of side 120 px, centered in
 * a given position and with a custom color.
 * 
 * @author rgarciacarmona
 */
public class Area {

	protected String name;
	private int time;
	private Position2D position;
	private Color color;
	protected int capacity;
	protected int numPatients;
	protected int waiting;

	/**
	 * Builds a new Area.
	 * 
	 * @param name     The name of the Area.
	 * @param time     The amount of time (in milliseconds) needed to treat a
	 *                 Patient in this Area.
	 * @param capacity The number of Patients that can be treated at the same time.
	 * @param position The location of the Area in the GUI.
	 */
	public Area(String name, int time, int capacity, Position2D position) {
		this.name = name;
		this.time = time;
		this.capacity = capacity;
		this.position = position;
		this.color = Color.GRAY; // Default color
	}

	/**
	 * Returns the name of the Area.
	 * 
	 * @return The name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the time (in milliseconds) that a Patient must spend in the Area to
	 * be treated.
	 * 
	 * @return The time.
	 */
	public int getTime() {
		return time;
	}

	/**
	 * Returns the position of the center of the Area in the GUI.
	 * 
	 * @return The position.
	 */
	public Position2D getPosition() {
		return position;
	}

	/**
	 * Returns the color of Area in the GUI.
	 * 
	 * @return The color.
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Changes the color of the Area in the GUI.
	 * 
	 * @param color The new color.
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	public synchronized void enter(Patient p) {
		try {
			// Región crítica: si la Area está llena y p tiene que esperar.
			while (numPatients >= capacity) {
				waiting++;
				System.out.println("El nido está lleno. El águila" + p + "tiene que esperar");
				wait();
			}
			// Cuando Area no está llena, el paciente entra
			waiting--;
			numPatients++;
			System.out.println("El águila" + p + "está en el nido");
		} catch (InterruptedException e) {
			System.out.println("Thread interrupted.");
		}
	}

	public synchronized void exit(Patient p) {
		numPatients--;
		notifyAll();
		System.out.println("El águila" + p + "ha salido del nido");
	}

	public synchronized int getCapacity() {
		return capacity;
	}

	public synchronized int getNumPatients() {
		return numPatients;
	}

	/**
	 * Returns the current number of Patients waiting to be treated at the Area.
	 * This method must be thread safe.
	 * 
	 * @return The number of Patients waiting to be treated.
	 */
	// TODO method getWaiting

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Area other = (Area) obj;
		return Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return name;
	}
}