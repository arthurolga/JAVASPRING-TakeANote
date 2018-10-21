package mvc.model;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime; 


public class Notas {
	private Integer id;
	private String titulo;
	private String content;
	private String tag;
	private String color;
	private String time;
	
	
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public void setColor(String color) {
		this.color=color;
	}
	
	public String getColor() {
		return color;
	}
	
	public String getTime(){
		
		return (time);
	}
	
	public void  setTime( String time) {
		this.time=time;
	}
		
	
	
	}
	
	

