package com.gme.hom.common.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class DataTypeConverter {

	public static  <T> List<T> castList(Class<? extends T> clazz, Collection<?> c) {
	    List<T> r = new ArrayList<T>(c.size());
	    for(Object o: c)
	      r.add(clazz.cast(o));
	    return r;
	}
}
