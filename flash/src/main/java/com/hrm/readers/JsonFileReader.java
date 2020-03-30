package com.hrm.readers;

public class JsonFileReader {

	 private final String customerFilePath = FileReaderManager.getInstance().getConfigReader().getTestDataResourcePath() + "Customer.json";
	 private List<Customer> customerList;
	 
	 public JsonDataReader(){
	 customerList = getCustomerData();
	 }
	 
	 private List<Customer> getCustomerData() {
	 Gson gson = new Gson();
	 BufferedReader bufferReader = null;
	 try {
	 bufferReader = new BufferedReader(new FileReader(customerFilePath));
	 Customer[] customers = gson.fromJson(bufferReader, Customer[].class);
	 return Arrays.asList(customers);
	 }catch(FileNotFoundException e) {
	 throw new RuntimeException("Json file not found at path : " + customerFilePath);
	 }finally {
	 try { if(bufferReader != null) bufferReader.close();}
	 catch (IOException ignore) {}
	 }
	 }
	 
	 public final Customer getCustomerByName(String customerName){
	 return customerList.stream().filter(x -> x.firstName.equalsIgnoreCase(customerName)).findAny().get();
	 }
}
