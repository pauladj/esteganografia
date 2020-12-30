

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class EsteganografiaPrograma {

	public static EsteganografiaPrograma unEsteganografiaPrograma;

	private EsteganografiaPrograma(){

	}

	public static EsteganografiaPrograma getEsteganografiaPrograma() {
		if(unEsteganografiaPrograma == null) {
			unEsteganografiaPrograma = new EsteganografiaPrograma();
		}
		return unEsteganografiaPrograma;
	}


	//Esconder texto en imagen (explicado en el informe)
	public void esconderTexto(String pDirImagen, String pMensaje, String pDirDestino) throws IOException {
		
/**		Basado en el código extraido de Stack Overflow
		Pregunta: https://stackoverflow.com/q/917163
		Respuesta: https://stackoverflow.com/a/34707749
		Autor R: https://stackoverflow.com/users/3065773/jay-remy
		Modificado por Paula de Jaime
		**/ 
		String resultado = "";
		char[] mensajeOriginal = pMensaje.toCharArray();

		for (int i = 0; i < mensajeOriginal.length; i++) {
			
			//Cada letra se convierte en binario -->  c es 1000011
			String letraBinario = Integer.toBinaryString(mensajeOriginal[i]);
			
			//Como se ha comentado en el informe, un caracter en binario puede ser como máximo de 7 dígitos
			//se utiliza la función rellenarCeros para asegurarse de que todos los números binarios que se añadan 
			//sean de 7 dígitos. Esto sirve para que al "sacar" el texto escondido se sepa donde termina y empieza 
			//cada número binario de cada letra.
			resultado += rellenarCeros(letraBinario);
		}
		
/**/
		
		 // asi al decodificar se sabe cuando se llega al final y no hace falta recorrer todos los pixeles de la imagen
		resultado += "1111111";

		//En resultado se tiene por ejemplo "CC%" en un string "10000111000011100101"
				
		//Se carga la imagen
		BufferedImage n = ImageIO.read(new File(pDirImagen));

		//Índice usado para saber que número de "resultado" toca usar
		int i=0;

		for(int f=1;f<n.getWidth() && i<resultado.length();f++) {
			for(int c=1;c<n.getHeight() && i<resultado.length();c++) {
				//Se obtiene un pixel en decimal
				int color = n.getRGB(f, c);

				ArrayList<String> colores = new ArrayList<String>();

			/**	Basado en el codigo extraido de Stack Overflow
				Pregunta: https://stackoverflow.com/q/25761438
				Autor P: https://stackoverflow.com/users/3996479/leonardo
				Respuesta: https://stackoverflow.com/a/25761560
				Autor R: https://stackoverflow.com/users/1705598/icza
				Modificado por Paula de Jaime
				**/ 				
				
				//Se obtienen los canales del pixel en binario
				colores.add(Integer.toBinaryString(color & 0xff)); //blue
				colores.add(Integer.toBinaryString((color & 0xff00) >> 8)); //green
				colores.add(Integer.toBinaryString((color & 0xff0000) >> 16)); //red
			/**/

				//Se cambia el último digito de los canales por el indicado por "i"
				//Si no hay más en "resultado" los demás pixeles se quedarán igual
				for(int j=0;i<resultado.length() && j<3;j++){
					colores.set(j, cambiarUltimoDigito(colores.get(j),resultado.charAt(i))); //se cambia el último dígito
					i++;
				}

				//Se crea el nuevo color del pixel siguiendo --> new Color(red, green, blue, alpha)
				//Como la imagen que queremos obtener no va a tener transparencias, alpha será 255 "11111111"
				//
				// Integer.valueOf("11010101", 2) -> lo que hace es convertir el string binario a decimal
				int rgba = new Color(Integer.valueOf(colores.get(2), 2),Integer.valueOf(colores.get(1),2),Integer.valueOf(colores.get(0),2),255).getRGB();
				
				//se asigna el nuevo color al pixel
				n.setRGB(f, c, rgba);

			}
		}

		//guardar imagen
		File outputfile = new File(pDirDestino);
		ImageIO.write(n, "png", outputfile);

	}

	// La longitud de pNumBinario será siete (explicado en informe)
	private String rellenarCeros(String pNumBinario) {
		int cerosARellenar = 7 - pNumBinario.length();
		for(int i=0;i<cerosARellenar;i++) {
			pNumBinario = "0" + pNumBinario;
		}
		return pNumBinario;

	}

	//Obtener texto escondido de imagen (explicado en el informe)
	public String obtenerTexto(String pDirImagen) throws IOException {
		//Será el texto final obtenido
		String texto = "";
		
		//Los bits que se van obteniendo de los canales
		String binarioTemp="";

		//Se lee la imagen
		BufferedImage n = ImageIO.read(new File(pDirImagen));

		for(int f=1;f<n.getWidth();f++) {
			for(int c=1;c<n.getHeight();c++) {
				//Se obtiene el pixel en decimal
				int color = n.getRGB(f, c);

			/**	Basado en el código extraído de Stack Overflow
				Pregunta: https://stackoverflow.com/q/25761438
				Autor P: https://stackoverflow.com/users/3996479/leonardo
				Respuesta: https://stackoverflow.com/a/25761560
				Autor R: https://stackoverflow.com/users/1705598/icza
				Modificado por Paula de Jaime
				**/ 				
				
				//Cada bit "menos significativo" de cada canal se guarda en "binarioTemp"
				binarioTemp = binarioTemp +  obtenerUltimoDigito(Integer.toBinaryString(color & 0xff)); //blue
				binarioTemp = binarioTemp + obtenerUltimoDigito(Integer.toBinaryString((color & 0xff00) >> 8)); //green
				binarioTemp = binarioTemp + obtenerUltimoDigito(Integer.toBinaryString((color & 0xff0000) >> 16)); //red
			/**/
				
				//Recordemos que se sabe que cada cáracter lo forman siete bits
				//Cuando hay siete o más bits en "binarioTemp", se procede a convertir los siete primeros bits en la letra correspondiente
				//Si hay siete 1s seguidos se ha terminado
				//
				//Integer.parseInt(letraBinaria,2) --> Se convierte un string a decimal
				//
				// Character.toString((char) letraANum) --> De decimal se convierte a letra
				//
				if(binarioTemp.length()>=7) {
					String letraBinaria = binarioTemp.substring(0, 7);
					if(Integer.parseInt(letraBinaria,2)==127) {
						return texto;
					}

					int letraANum = Integer.valueOf(letraBinaria,2);
					texto += Character.toString((char) letraANum); 
					binarioTemp = binarioTemp.substring(7);
				}
			}
		}
		return texto;
	}

	//Se obtiene el último digito de pBinaryString (explicado en informe)
	private String obtenerUltimoDigito(String pBinaryString) {
		char[] arrayBinario = pBinaryString.toCharArray();
		char ultimoDigito = arrayBinario[arrayBinario.length-1];
		return String.valueOf(ultimoDigito);
	}
	
	//Se cambia el último dígito de pColor por pDigito (explicado en informe)
	private String cambiarUltimoDigito(String pColor, char pDigito) {
		char[] arrayBinario = pColor.toCharArray();
		arrayBinario[arrayBinario.length-1] = pDigito;
		return new String(arrayBinario);
	}


}
