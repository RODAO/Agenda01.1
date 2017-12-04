package br.com.alura.agenda01.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import br.com.alura.agenda01.modelo.Aluno;

/**
 * Created by DELL on 20/09/2017.
 */
public class AlunoDAO extends SQLiteOpenHelper {
    public AlunoDAO(Context context) {
        super(context, "Agenda", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Alunos (id INTEGER PRIMARY KEY, " +
                "nome TEXT NOT NULL, " +
                "endereco TEXT NOT NULL, " +
                "telefone TEXT, " +
                "email TEXT, " +
                "site TEXT, " +
                "nota REAL, " +
                "caminhoFoto TEXT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "";
        switch (oldVersion) {
            case 1:
                sql = "ALTER TABLE Alunos ADD COLUMN caminhoFoto TEXT";
                db.execSQL(sql); //indo para a versão 2
        }
    }

    public void insere(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosDoAluno(aluno);

        db.insert("Alunos", null, dados);
    }

        //@NonNull
        private ContentValues pegaDadosDoAluno(Aluno aluno) {
            ContentValues dados = new ContentValues();
            dados.put("nome", aluno.getNome());
            dados.put("endereco", aluno.getEndereco());
            dados.put("telefone", aluno.getTelefone());
            dados.put("email", aluno.getEmail());
            dados.put("site", aluno.getSite());
            dados.put("nota", aluno.getNota());
            dados.put("caminhoFoto", aluno.getCaminhofoto());
            return dados;
        }

    public void alterar(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = new ContentValues();
        dados.put("nome", aluno.getNome());
        dados.put("endereco", aluno.getEndereco());
        dados.put("telefone", aluno.getTelefone());
        dados.put("email", aluno.getEmail());
        dados.put("site", aluno.getSite());
        dados.put("nota", aluno.getNota());

        db.update("Alunos", dados, "id = " + aluno.getId(), null);
    }

    public ArrayList<Aluno> buscaAlunos() {
        String sql = "SELECT * FROM Alunos;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        ArrayList<Aluno> alunos = new ArrayList<Aluno>();
        if (c != null && c.moveToFirst()) {
            while (c.moveToNext()) {
                Aluno aluno = new Aluno();
                aluno.setId(c.getLong(c.getColumnIndex("id")));
                aluno.setNome(c.getString(c.getColumnIndex("nome")));
                aluno.setEndereco(c.getString(c.getColumnIndex("endereco")));
                aluno.setTelefone(c.getString(c.getColumnIndex("telefone")));
                aluno.setEmail(c.getString(c.getColumnIndex("email")));
                aluno.setSite(c.getString(c.getColumnIndex("site")));
                aluno.setNota(c.getDouble(c.getColumnIndex("nota")));
                aluno.setCaminhofoto(c.getString(c.getColumnIndex("caminhoFoto")));

                alunos.add(aluno);
            }
        }
        c.close();

        return alunos;
    }

    public void excluir(Aluno aluno) {
        String sql = "DELETE FROM Alunos WHERE nome LIKE ?;";
        SQLiteDatabase db = getWritableDatabase();
        db.delete("Alunos", "nome LIKE '" + aluno.getNome() + "%'", null);
    }
}
