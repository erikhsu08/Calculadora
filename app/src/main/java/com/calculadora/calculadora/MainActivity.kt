package com.calculadora.calculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.calculadora.calculadora.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val num0 = binding.num0
        val num1 = binding.num1
        val num2 = binding.num2
        val num3 = binding.num3
        val num4 = binding.num4
        val num5 = binding.num5
        val num6 = binding.num6
        val num7 = binding.num7
        val num8 = binding.num8
        val num9 = binding.num9
        val soma = binding.soma
        val subtracao = binding.subtracao
        val multiplicacao = binding.multiplicacao
        val divisao = binding.divisao
        val expressao = binding.expressao
        val txtResultado = binding.txtResultado
        val limpar = binding.limpar
        val backspace = binding.backspace
        val ponto = binding.ponto
        val igual = binding.igual

        //Números
        num0.setOnClickListener { AcrescentarUmaExpressao("0", true) }
        num1.setOnClickListener { AcrescentarUmaExpressao("1", true) }
        num2.setOnClickListener { AcrescentarUmaExpressao("2", true) }
        num3.setOnClickListener { AcrescentarUmaExpressao("3", true) }
        num4.setOnClickListener { AcrescentarUmaExpressao("4", true) }
        num5.setOnClickListener { AcrescentarUmaExpressao("5", true) }
        num6.setOnClickListener { AcrescentarUmaExpressao("6", true) }
        num7.setOnClickListener { AcrescentarUmaExpressao("7", true) }
        num8.setOnClickListener { AcrescentarUmaExpressao("8", true) }
        num9.setOnClickListener { AcrescentarUmaExpressao("9", true) }
        ponto.setOnClickListener { AcrescentarUmaExpressao(".", true)}

        //Operadores
        soma.setOnClickListener { AcrescentarUmaExpressao("+", false) }
        subtracao.setOnClickListener { AcrescentarUmaExpressao("-", false) }
        multiplicacao.setOnClickListener { AcrescentarUmaExpressao("*", false) }
        divisao.setOnClickListener { AcrescentarUmaExpressao("/", false) }

        //Botão de limpar resultado e toda a expressão
        limpar.setOnClickListener {
            expressao.text = ""
            txtResultado.text = ""
        }

        //Botão de backspace
        backspace.setOnClickListener {
            val string = expressao.text.toString()
            if (string.isNotBlank()){
                expressao.text = string.substring(0, string.length-1)
            }
            txtResultado.text = ""
        }

        //Botão de igual
        igual.setOnClickListener {
            try {
                val expressao = ExpressionBuilder(expressao.text.toString()).build()
                val resultado = expressao.evaluate()
                val longResult = resultado.toLong()

                if (resultado == longResult.toDouble())
                    txtResultado.text = longResult.toString()
                else
                    txtResultado.text = resultado.toString()

            }catch (e: Exception){

            }
        }

    }

    fun AcrescentarUmaExpressao(string: String, limpar_dados: Boolean){
        if(binding.txtResultado.text.isNotEmpty()){
            binding.expressao.text = ""
        }

        if (limpar_dados){
            binding.txtResultado.text = ""
            binding.expressao.append(string)
        }else{
            binding.expressao.append(binding.txtResultado.text)
            binding.expressao.append(string)
            binding.txtResultado.text = ""
        }
    }

}