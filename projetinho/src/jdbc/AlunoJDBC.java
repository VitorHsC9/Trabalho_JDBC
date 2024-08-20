package jdbc;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.sql.ResultSet;
import java.sql.Statement;

import entities.Aluno;


public class AlunoJDBC {

	Connection con;
	String sql;
	PreparedStatement pst;
	
	public void salvar(Aluno a) throws SQLException {
	    try {
	        con = db.getConexao();
	        sql = "INSERT INTO aluno (nome, sexo, dt_nasc) VALUES (?, ?, ?)";
	        pst = con.prepareStatement(sql);
	        pst.setString(1, a.getNome());
	        pst.setString(2, a.getSexo());
	        Date dataSql = Date.valueOf(a.getDt_nasc());
	        pst.setDate(3, dataSql);
	        pst.executeUpdate();
	        System.out.println("\nO cadastro do aluno realizado com sucesso!");
	    } catch (Exception e) {
	        System.out.println(e);
	    } finally {
	        try {
	            if (pst != null) pst.close();
	            db.closeConexao();
	        } catch (SQLException e) {
	            System.out.println(e);
	        }
	    }
	}

    public List<Aluno> listar() throws IOException {
        List<Aluno> alunos = new ArrayList<>();
        try {
            con = db.getConexao();
            sql = "SELECT * FROM aluno";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setId(rs.getInt("id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setSexo(rs.getString("sexo"));
                aluno.setDt_nasc(rs.getDate("dt_nasc").toLocalDate());
                alunos.add(aluno);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar alunos: " + e.getMessage());
        } finally {
            try {
                if (con != null) con.close(); 
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
        return alunos;
    }


	
	public void apagar(int id) {
	    try {
	        con = db.getConexao();
	        sql = "DELETE FROM aluno WHERE id = ?";
	        pst = con.prepareStatement(sql);
	        pst.setInt(1, id);
	        int rowsAffected = pst.executeUpdate();

	        if (rowsAffected > 0) {
	            System.out.println("O aluno foi apagado com sucesso.");
	        } else {
	            System.out.println("Nenhum aluno encontrado com esse id.");
	        }
	    } catch (Exception e) {
	        System.out.println(e);
	    } finally {
	        try {
	            if (pst != null) pst.close(); 
	            db.closeConexao(); 
	        } catch (SQLException e) {
	            System.out.println(e);
	        }
	    }
	}

	
	public void alterar(Aluno a) {
	    try {
	        con = db.getConexao();
	        sql = "UPDATE aluno SET nome = ?, sexo = ?, dt_nasc = ? WHERE id = ?";
	        pst = con.prepareStatement(sql);
	        pst.setString(1, a.getNome());
	        pst.setString(2, a.getSexo());
	        pst.setDate(3, Date.valueOf(a.getDt_nasc()));
	        pst.setInt(4, a.getId());
	        int rowsAffected = pst.executeUpdate();

	        if (rowsAffected > 0) {
	            System.out.println("O aluno foi alterado com sucesso.");
	        } else {
	            System.out.println("Nenhum aluno foi encontrado com esse id.");
	        }

	    } catch (Exception e) {
	        System.out.println(e);
	    } finally {
	        try {
	            pst.close();
	            db.closeConexao();
	        } catch (SQLException e) {
	            System.out.println(e);
	        }
	    }
	}
}
