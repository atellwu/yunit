package org.adclear.dbunit.json;

import java.lang.annotation.ElementType;

import lombok.Getter;

import org.adclear.dbunit.json.annotations.JsonData;
import org.junit.runners.model.FrameworkMethod;

/**
 * @author fit
 *
 */
@Getter
public class JsonDataResult {

	private JsonData jsonData;

	private ElementType elementType;

	private FrameworkMethod testMethod;

	public JsonDataResult() {
		super();
	}

	public JsonDataResult(JsonData jsonData, ElementType elementType, FrameworkMethod testMethod) {
		super();
		this.jsonData = jsonData;
		this.elementType = elementType;
		this.testMethod = testMethod;
	}

	public boolean isJsonDataPresent() {
		return this.jsonData != null;
	}

	public String getFileName() {
		if (this.isJsonDataPresent()) {
			return getFileName(this);
		}
		return null;
	}

	private static String getFileName(JsonDataResult jsonDataResult) {
		JsonData data = jsonDataResult.getJsonData();
		String dataSetFileName = data.fileName();
		if (dataSetFileName.isEmpty()) {
			dataSetFileName = getDefaultDataSetFileName(jsonDataResult, "json");
		}
		return dataSetFileName;
	}

	private static String getDefaultDataSetFileName(JsonDataResult jsonDataResult, String extension) {
		if (jsonDataResult.getElementType() == ElementType.METHOD) {

			String className = jsonDataResult.getTestMethod().getMethod().getDeclaringClass().getSimpleName();
			return className + "-" + jsonDataResult.getTestMethod().getName() + "." + extension;
		} else if (jsonDataResult.getElementType() == ElementType.TYPE) {

			String className = jsonDataResult.getTestMethod().getMethod().getDeclaringClass().getSimpleName();
			return className + "." + extension;
		}
		return null;
	}

	public JsonData getJsonData() {
		return this.jsonData;
	}

	public void setJsonData(JsonData jsonData) {
		this.jsonData = jsonData;
	}

	public ElementType getElementType() {
		return this.elementType;
	}

	public void setElementType(ElementType elementType) {
		this.elementType = elementType;
	}

	public FrameworkMethod getTestMethod() {
		return this.testMethod;
	}

	public void setTestMethod(FrameworkMethod testMethod) {
		this.testMethod = testMethod;
	}

}
