package game;

public enum Sprite {
	PEACH(0,"peach"),
	LUIGI(1,"luigi");
	
	private int id;
	private String nombre;

	private Sprite(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}
	
	public static Sprite SpriteById(int id) {
		for (Sprite sprite : values()) {
			if(sprite.getId() == id)
				return sprite;
		}
		return null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
