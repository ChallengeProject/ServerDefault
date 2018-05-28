package me.hackathon.root.support.enumeration;

public interface CodeEnum<T extends Enum<T> & CodeEnum<T>> {
	String getCode();
}
