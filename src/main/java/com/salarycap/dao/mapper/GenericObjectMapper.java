package com.salarycap.dao.mapper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.salarycap.annotations.Column;
import com.salarycap.annotations.PropertyName;
import com.salarycap.annotations.TemplateConstructor;

public class GenericObjectMapper<T> implements RowMapper<T>{
	
    final Class<T> classType;

    public GenericObjectMapper(Class<T> typeParameterClass) {
        this.classType = typeParameterClass;
    }

	@SuppressWarnings("unchecked")
	@Override
	public T mapRow(ResultSet rs, int rowNum) throws SQLException {
		//Declare a constructor that matches the constructor declared
		//as the template constructor. This is a constructor that has
		//a paramater for each property in the class. Each paramater
		//is annotated with @ConstructorParam specifying the name
		//of the field it represents
		Constructor<?> constr = null;
		for (Constructor<?> c : classType.getConstructors()) {
			for (Annotation a : c.getDeclaredAnnotations()) {
				if(a.annotationType()==TemplateConstructor.class){
					constr = c;
				}
			}
		}
		//Declare an array of objects that will be passed as the paramater
		//when creating the object
		Object[] args = new Object[constr.getParameterTypes().length];
		//get a list of all the annotations inside the constructor
		Annotation[][] constrAnt = constr.getParameterAnnotations();
		//Declare an array of strings to store the field names
		String[] fieldNames = new String[args.length];
		//loop through the array of annotations, setting the fieldName equal to the value
		//of the ConstructorParam annotation to the current 
		for (int i = 0; i < constrAnt.length; i++) {
			fieldNames[i] = ((PropertyName)constrAnt[i][0]).value();
		}
		
		//Set the arguments
		for(int i = 0; i<args.length; i++) {
			try {
				//get the property by field name
				//get the Column annotation on that field (represents the name of the field in the db)
				//get that value as an object
				//cast the value to paramater type at position i
				//set the value to the element at index i inside the args array
				args[i] = constr.getParameterTypes()[i]
						.cast(rs.getObject(classType.getDeclaredField(fieldNames[i])
						.getAnnotation(Column.class).value()));
			} catch (NoSuchFieldException | SecurityException e) {
				e.printStackTrace();
			} catch(ClassCastException e){
				e.toString();
			}
		}
		try {
			return (T) constr.newInstance(args);
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}		
	}

}
