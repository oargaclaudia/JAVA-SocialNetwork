package com.repo;

import com.domain.Utilizator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtilizatorDBRepository implements com.repo.Repository<String,Utilizator> {

    private final String url; //url-ul stie la ce baza de date sa se conecteze
    private final String username; //username-ul bazei de date(la noi se numeste postgres)
    private final String password; //parola bazei de date(la mine o sa fie "smiley10")

    //constructor pt utilizatorul din baza de date
    public UtilizatorDBRepository(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public List<Utilizator> getAll() {
        List<Utilizator> utilizatori = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, username, password); //pt
             //a ne conecta la baza de date
             PreparedStatement statement = connection.prepareStatement("SELECT * from \"Users\"");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String id = resultSet.getString("id"); //id-ul utilizatorului
                String email = resultSet.getString("email"); //email-ul utilizatorului
                String parola = resultSet.getString("parola");//parola utilizatorului
                String nume = resultSet.getString("nume"); //numele utilizatorului
                String prenume = resultSet.getString("prenume"); //prenumele utilizatorului

                Utilizator user = new Utilizator(id, email, parola, nume, prenume);
                utilizatori.add(user);
            }
            return utilizatori;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utilizatori;
    }

    //adauga un utilizator in baza de date
    @Override
    public void adauga(Utilizator utilizator) {
    //compilatorul asteapta ca eu sa introduc valorile

        String sql = "insert into \"Users\" (id,email,parola,nume,prenume) values (?,?,?,?,?)";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {
        //aici imi pun atributele clasei Utilizator
            ps.setString(1, utilizator.getId());
            ps.setString(2, utilizator.getEmail());
            ps.setString(3, utilizator.getParola());
            ps.setString(4, utilizator.getNume());
            ps.setString(5, utilizator.getPrenume());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //sterge un utilizator din baza de date
    @Override
    public void sterge(Utilizator utilizator) {
        //asteapta id-ul Utilizatorului
        String sql = "delete from \"Users\" where id = ?";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, utilizator.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //cauta utilizatorul dupa un id
    @Override
    public Utilizator cautaId(String id){
        String sql = "select * from \"Users\" where id = ? ";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, id);
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();

            //preia utilizatorii din tabel
            String email = resultSet.getString("email");
            String parola = resultSet.getString("parola");
            String nume = resultSet.getString("nume");
            String prenume = resultSet.getString("prenume");
            return new Utilizator(id, email, parola, nume, prenume);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //actualizeaza un utilizator vechi cu unul nou
    @Override
    public void update(Utilizator utilizatorVechi,Utilizator utilizatorNou){
        String sql = "update \"Users\" set email = ?, parola = ?, nume = ?,prenume = ? where id = ?";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1,utilizatorNou.getEmail());
            ps.setString(2, utilizatorNou.getParola());
            ps.setString(3, utilizatorNou.getNume());
            ps.setString(4, utilizatorNou.getPrenume());
            ps.setString(5, utilizatorVechi.getId());

            ps.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

}