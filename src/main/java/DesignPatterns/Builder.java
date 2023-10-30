package DesignPatterns;

public class Builder {

}

/*
*
*
* // Java code to demonstrate Builder Pattern

// Server Side Code
final class Student {

	// final instance fields
	private final int id;
	private final String name;
	private final String address;

	public Student(Builder builder)
	{
		this.id = builder.id;
		this.name = builder.name;
		this.address = builder.address;
	}

	// Static class Builder
	public static class Builder {

		/// instance fields
		private int id;
		private String name;
		private String address;

		public static Builder newInstance()
		{
			return new Builder();
		}

		private Builder() {}

		// Setter methods
		public Builder setId(int id)
		{
			this.id = id;
			return this;
		}
		public Builder setName(String name)
		{
			this.name = name;
			return this;
		}
		public Builder setAddress(String address)
		{
			this.address = address;
			return this;
		}

		// build method to deal with outer class
		// to return outer instance
		public Student build()
		{
			return new Student(this);
		}
	}

	@Override
	public String toString()
	{
		return "id = " + this.id + ", name = " + this.name +
							", address = " + this.address;
	}
}

// Client Side Code
class StudentReceiver {

	// volatile student instance to ensure visibility
	// of shared reference to immutable objects
	private volatile Student student;

	public StudentReceiver()
	{

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run()
			{
				student = Student.Builder.newInstance()
							.setId(1)
							.setName("Ram")
							.setAddress("Noida")
							.build();
			}
		});

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run()
			{
				student = Student.Builder.newInstance()
							.setId(2)
							.setName("Shyam")
							.setAddress("Delhi")
							.build();
			}
		});

		t1.start();
		t2.start();
	}

	public Student getStudent()
	{
		return student;
	}
}

// Driver class
public class BuilderDemo {
	public static void main(String args[])
	{
		StudentReceiver sr = new StudentReceiver();
		System.out.println(sr.getStudent());
	}
}

*
* */