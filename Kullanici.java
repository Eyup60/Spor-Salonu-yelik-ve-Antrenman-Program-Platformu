package sporSalonuÜyelikVeAntrenmanProgramı;

import java.util.UUID;

public abstract class Kullanici {
    
    private String id;
    private String email;
    private String password;
    private Role role;
    
    public Kullanici(String email, String password, Role role) {
        this.id = UUID.randomUUID().toString();
        setEmail(email);
        setPassword(password);
        setRole(role);
    }
    
    public String getId() { return id; }
    public String getEmail() { return email; }
    
    public void setEmail(String email) {
        if(email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email boş olamaz!");
        }
        if(!email.contains("@") || !email.contains(".") || email.indexOf("@") > email.lastIndexOf(".")) {
            throw new IllegalArgumentException("Geçerisiz giriş");
        }
        this.email = email.trim();
    }
    
    public String getPassword() { return password; }
    
    public void setPassword(String password) {
        if(password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Şifre boş olamaz!");
        }
        if(password.length() < 6) {
            throw new IllegalArgumentException("Şifre en az 6 haneli olmalıdır");
        }
        this.password = password;
    }
    
    public Role getRole() { return role; }
    
    public void setRole(Role role) {
        if(role == null) {
            throw new IllegalArgumentException("Rol boş olamaz!");
        }
        this.role = role;
    }
    
    public abstract void displayInfo();
    
    public boolean login(String email, String password) {
        if(email == null || password == null) {
            return false;
        }
        return this.email.equalsIgnoreCase(email.trim()) && this.password.equals(password);
    }
    
    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || !(obj instanceof Kullanici)) return false;
        
        Kullanici k = (Kullanici) obj;
        return getId().equals(k.getId()) || getEmail().equalsIgnoreCase(k.getEmail().trim());
    }
    
    @Override
    public int hashCode() {
        return getId().hashCode();
    }
    
    @Override
    public String toString() {
        return "Email: " + email + " | Role: " + role;
    }
}