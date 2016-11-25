package com.github.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "hotlanguage")
public class HotLang implements Serializable,HotWord {

	private static final long serialVersionUID = -5785857960597910259L;

	private String id;
	private String word;
	private double weight;

	/**
	 * <br>
	 * ------------------------------<br>
	 */
	public HotLang() {
	}

	/**
	 *
	 * <br>
	 * ------------------------------<br>
	 * 
	 * @param id
	 * @param name
	 * @param age
	 */
	public HotLang(String id, String word, double weight) {
		super();
		this.id = id;
		this.word = word;
		this.weight = weight;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "HotLang [id=" + id + ", word=" + word + ", weight=" + weight + "]";
	}

}
