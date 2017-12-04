package br.com.alura.agenda01;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import br.com.alura.agenda01.modelo.Aluno;

/**
 * Created by DELL on 19/09/2017.
 */
public class FormularioHelper {

    private final EditText campoNome;
    private final EditText campoEndereco;
    private final EditText campoTelefone;
    private final EditText campoEmail;
    private final EditText campoSite;
    private final RatingBar campoNota;
    private final ImageView campoFoto;

    private Aluno aluno;

    public FormularioHelper(FormularioActivity activity) {
        campoNome = (EditText) activity.findViewById(R.id.formulario_nome);
        campoEndereco = (EditText) activity.findViewById(R.id.formulario_endereco);
        campoTelefone = (EditText) activity.findViewById(R.id.formulario_telefone);
        campoEmail = (EditText) activity.findViewById(R.id.formulario_email);
        campoSite = (EditText) activity.findViewById(R.id.formulario_site);
        campoNota = (RatingBar) activity.findViewById(R.id.formulario_nota);
        campoFoto = (ImageView) activity.findViewById(R.id.formulario_foto);
        this.aluno = new Aluno();

    }

    public Aluno pegaAluno() {
        boolean alunoIncompleto = false;
        //Aluno aluno = new Aluno();
        if (!campoNome.getText().toString().equals("")) {
            aluno.setNome(campoNome.getText().toString());
        } else {
            campoNome.setError("Este campo não pode ficar vazio");
            alunoIncompleto = true;
        }
        //aluno.setEndereco(campoEndereco.getText().toString());
        if (!campoEndereco.getText().toString().equals("")) {
            aluno.setEndereco(campoEndereco.getText().toString());
        } else {
            campoEndereco.setError("Este campo não pode ficar vazio");
            alunoIncompleto = true;
        }
        if (!campoTelefone.getText().toString().equals("")) {
            aluno.setTelefone(campoTelefone.getText().toString());
        } else {
            campoTelefone.setError("Este campo não pode ficar vazio");
            alunoIncompleto = true;
        }

        if (!campoEmail.getText().toString().equals("")) {
            aluno.setEmail(campoEmail.getText().toString());
        } else {
            campoEmail.setError("Este campo não pode ficar vazio");
            alunoIncompleto = true;
        }

        aluno.setSite(campoSite.getText().toString());
        if (!campoSite.getText().toString().equals("")) {
            aluno.setEmail(campoSite.getText().toString());
        } else {
            campoSite.setError("Este campo não pode ficar vazio");
            alunoIncompleto = true;
        }
        aluno.setNota(Double.valueOf(campoNota.getProgress()));
        aluno.setCaminhofoto((String) campoFoto.getTag());
        if (alunoIncompleto == true) {
            return null;
        } else {
            return aluno;
        }
    }

    public void preencherFormulario(Aluno aluno) {
        campoNome.setText(aluno.getNome());
        campoEndereco.setText(aluno.getEndereco());
        campoTelefone.setText(aluno.getTelefone());
        campoSite.setText(aluno.getSite());
        campoEmail.setText(aluno.getEmail());
        campoNota.setProgress((int) aluno.getNota());
        carregaImagem(aluno.getCaminhofoto());
        this.aluno = aluno;
    }

    public void carregaImagem(String caminhoFoto) {
        if (caminhoFoto != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
            Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
            campoFoto.setImageBitmap(bitmapReduzido);
            campoFoto.setScaleType(ImageView.ScaleType.FIT_XY);
            campoNome.setTag(caminhoFoto);
        }
    }
}

