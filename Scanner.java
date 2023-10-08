import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Scanner {

    private static final Map<String, TipoToken> palabrasReservadas;

    static {
        palabrasReservadas = new HashMap<>();
        palabrasReservadas.put("and", TipoToken.AND);
        palabrasReservadas.put("else", TipoToken.ELSE);
        palabrasReservadas.put("false", TipoToken.FALSE);
        palabrasReservadas.put("for", TipoToken.FOR);
        palabrasReservadas.put("fun", TipoToken.FUN);
        palabrasReservadas.put("if", TipoToken.IF);
        palabrasReservadas.put("null", TipoToken.NULL);
        palabrasReservadas.put("or", TipoToken.OR);
        palabrasReservadas.put("print", TipoToken.PRINT);
        palabrasReservadas.put("return", TipoToken.RETURN);
        palabrasReservadas.put("true", TipoToken.TRUE);
        palabrasReservadas.put("var", TipoToken.VAR);
        palabrasReservadas.put("while", TipoToken.WHILE);
    }

    private final String source;

    private final List<Token> tokens = new ArrayList<>();

    public Scanner(String source) {
        this.source = source + " ";
    }

    
    public List<Token> scan() throws Exception {
        String lexema = "";
        int estado = 0;
        char c;

        for(int i=0; i<source.length(); i++){
            c = source.charAt(i);

            switch (estado){
//--------------------------ESTADO DE INICIO------------------------
case 0:
if(Character.isLetter(c)){
    //ESTADO 9
    estado = 11;
    lexema += c;
}
else if(Character.isDigit(c)){
    //ESTADO 12

    estado = 13;
    lexema += c; 
}
else if(c==' ' || c=='\t' || c=='\r' || c=='\n'){
    // ignoramos estos caracteres
}
else{//PARA LOS SIMBOLOS
    i--;
    estado = 1;
}

break;
//------------------------------IDENTIFICADORES O PALABRAS RESERVADAS-----------------------------------
case 11:
if(Character.isLetter(c) || Character.isDigit(c)){
estado = 11;
lexema += c;
}
else{
// Vamos a crear el Token de identificador o palabra reservada
TipoToken tt = palabrasReservadas.get(lexema);
if(tt == null){
    Token t = new Token(TipoToken.IDENTIFIER, lexema);
    tokens.add(t);
}
else{
    Token t = new Token(tt, lexema);
    tokens.add(t);
}
estado = 0;
lexema = "";
i--;
}
break;
//--------------------------------DIGITOS------------------------------------------
//CONTRERAS GONZALEZ ENRIQUE GABRIEL
case 13:
if(Character.isDigit(c)){
    estado = 13;
    lexema += c;
}
else if(c == '.'){
     estado = 14;
    lexema += c;
}
else if(c == 'E'){
    estado = 16;
    lexema += c;
}
else{
    //estado = 20;
    Token t = new Token(TipoToken.NUMBER, lexema, Integer.valueOf(lexema));
    tokens.add(t);
    estado = 0;
    lexema = "";
    i--;
}
break;


case 14:
if(Character.isDigit(c)){
    estado = 15;
    lexema += c;
}
else{
    estado =-1;
}
break;
case 15:
if(Character.isDigit(c)){
    estado = 15;
    lexema += c;
}

else if(c == 'E'){
     estado = 16;
    lexema += c;
}
else{
   // estado = 21;
   Token t = new Token(TipoToken.NUMBER, lexema, Double.valueOf(lexema));
    tokens.add(t);
    estado = 0;
    lexema = "";
    i--;
}
break;

case 18:
if(Character.isDigit(c)){
    estado = 18;
    lexema += c;
}
   
else{
    //estado =19;
    Token t = new Token(TipoToken.NUMBER, lexema, Double.valueOf(lexema));
    tokens.add(t);
    estado = 0;
    lexema = "";
    i--;
    
}
break;

case 17:
if(Character.isDigit(c)){
    estado = 18;
    lexema += c;
}
   
else{
    estado =-1;
    
}
break;
case 16:
if(Character.isDigit(c)){
    estado = 18;
    lexema += c;
}
else if(c=='+' ||  c=='-'){
    estado = 17;
    lexema += c;
}
   
else{
    estado =-1;
    
}
break;
//--------------------------SIMBOLOS--------------------------------
//CID MARTINEZ KARLA IVETH--------------------------------------------------------
case 1:
if(c =='('){
    lexema +=c;
Token t = new Token(TipoToken.LEFT_PAREN, lexema);
        tokens.add(t);
        lexema = "";
        estado = 0;
        
    
}  
else if(c ==')'){
    lexema +=c;
    Token t = new Token(TipoToken.RIGHT_PAREN, lexema);
        tokens.add(t);
        estado = 0;
        lexema = "";
}     
else if(c =='{'){
    lexema +=c;
    Token t = new Token(TipoToken.LEFT_BRACE, lexema);
        tokens.add(t);
        estado = 0;
        lexema = "";
}
else if(c =='}'){
    lexema +=c;
    Token t = new Token(TipoToken.RIGHT_BRACE, lexema);
        tokens.add(t);
        estado = 0;
        lexema = "";
} 
else if(c ==','){
    lexema +=c;
    Token t = new Token(TipoToken.COMMA, lexema);
        tokens.add(t);
        estado = 0;
        lexema = "";
}
else if(c =='.'){
    lexema +=c;
    Token t = new Token(TipoToken.DOT, lexema);
        tokens.add(t);
        estado = 0;
        lexema = "";
} 
else if(c =='-'){
    lexema +=c;
    Token t = new Token(TipoToken.MINUS, lexema);
        tokens.add(t);
        estado = 0;
        lexema = "";
} 
else if(c =='+'){
    lexema +=c;
    Token t = new Token(TipoToken.PLUS, lexema);
        tokens.add(t);
        estado = 0;
        lexema = "";
}   
else if(c ==';'){
    lexema +=c;
    Token t = new Token(TipoToken.SEMICOLON, lexema);
        tokens.add(t);
        estado = 0;
        lexema = "";
} 

else if(c =='*'){
    lexema +=c;
    Token t = new Token(TipoToken.STAR, lexema);
        tokens.add(t);
        estado = 0;
        lexema = "";
}    

else if(c =='='){
     lexema +=c;
    estado = 3;
} 
else if(c =='>'){
     lexema +=c;
    estado = 4;
}   
else if(c =='<'){
     lexema +=c;
    estado = 5;
}
else if(c =='!'){
    lexema +=c;
    estado = 2;
}
 else if(c =='/'){
     lexema +=c;
    estado =6 ;
} 
else if(c =='"'){
    estado =7;    
}
break;
case 6:
if(c=='/'){
estado =8;
}
else if(c=='*'){
estado =9;
}
else{
    Token t = new Token(TipoToken.SLASH, lexema);
        tokens.add(t);
        estado = 0;
        lexema = "";
        i--;
}
break;
case 8:
if(c=='\n'){

estado =0;
lexema = "";
}
else{
    estado =8;
}
break;
case 9:
if(c=='*'){
estado =10;

}
else{
    estado =9;
}
break;
case 10:
if(c=='/'){
estado =0;
lexema = "";

}
else{
    estado =10;
}
break;
case 2:
if(c =='='){
lexema +=c;
            Token t = new Token(TipoToken.BANG_EQUAL, lexema);
            tokens.add(t);
            estado = 0;
            lexema="";
    }
    else{
        Token t = new Token(TipoToken.BANG, lexema);
            tokens.add(t);
            estado = 0;
            lexema="";
            i--;
    }
break;

case 3:
if(c =='='){
lexema +=c;
            Token t = new Token(TipoToken.EQUAL_EQUAL, lexema);
            tokens.add(t);
            estado = 0;
            lexema="";
    }
    else{
        Token t = new Token(TipoToken.EQUAL, lexema);
            tokens.add(t);
            estado = 0;
            lexema="";
            i--;
    }
break;
case 4:
if(c =='='){
lexema +=c;
            Token t = new Token(TipoToken.GREATER_EQUAL, lexema);
            tokens.add(t);
            estado = 0;
            lexema="";
    }
    else{
        Token t = new Token(TipoToken.GREATER, lexema);
            tokens.add(t);
            estado = 0;
            lexema="";
            i--;
    }
break;
case 5:
if(c =='='){
lexema +=c;
            Token t = new Token(TipoToken.LESS_EQUAL, lexema);
            tokens.add(t);
            estado = 0;
            lexema="";
    }
    else{
        Token t = new Token(TipoToken.LESS, lexema);
            tokens.add(t);
            estado = 0;
            lexema="";
            i--;
    }
break;
case 7:
if(c=='"'){
    Token t = new Token(TipoToken.STRING, lexema,  String.valueOf(lexema) );
            tokens.add(t);
            estado = 0;
            lexema="";
}
else if(c=='\n'){
    estado =-1;
}
else{
estado =7;
lexema +=c;
}
break;
// -----------------------Estado de error-----------------------
case -1: 
estado = 0; 
lexema = "";
i--;
break;
            
        }

    }
        return tokens;
    }
        private void print(String string) {
    }
}