package utils;

//{"message":"保存成功","data":null,"success":true}
public class Result {

	public String message;
	public String data;
	public boolean success;
	public Long billId;
	
	public Result() {
		this.success = false;
		this.message = "保存失败";
	}
	
	public Result(boolean success,Long billId) {
		this.success = success;
		this.billId = billId;
		if(this.success) this.message = "保存成功";
		else this.message = "保存失败";
	}
}
