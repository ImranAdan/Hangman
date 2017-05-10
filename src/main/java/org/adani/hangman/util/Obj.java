package org.adani.hangman.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Obj {

	private final InnerObj inner;
	
	public Obj(Integer k, String v){
		inner = new InnerObj(k, v);
	}

	
	private static class InnerObj{
		private final Map<Integer, String> a;
		
		public InnerObj(Integer k, String v){
			a = (Collections.singletonMap(k, v));
		}

		public Map<Integer, String> getA() {
			return a;
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((a == null) ? 0 : a.hashCode());
			return result;
		}


		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			InnerObj other = (InnerObj) obj;
			return a.equals(other.a);
		}
		
	}


	@Override
	public int hashCode() {
		return 31 * inner.hashCode();
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Obj other = (Obj) obj;
		return inner.equals(other.inner);
	}
	
}
