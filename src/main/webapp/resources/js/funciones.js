/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var row;
var rowOld;

function trim(str){
    var cad=str.value;
    if(!cad || typeof cad != 'string')
        return '';

    return cad.replace(/^[\s]+/,'').replace(/[\s]+$/,'').replace(/[\s]{2,}/,' ');
}

function soloLetras(elEvento) {
    // Variables que definen los caracteres permitidos
    var permitidos = " abcdefghijklmnñopqrstuvwxyzABCDEFGHIJKLMNÑOPQRSTUVWXYZáéíóúÁÉÍÓÚ";
    var teclas_especiales = [8, 37, 39, 46,9];
    // 8 = BackSpace, 46 = Supr, 37 = flecha izquierda, 39 = flecha derecha, 9 tab

    // Seleccionar los caracteres a partir del parámetro de la función
    var evento = elEvento || window.event;
    var codigoCaracter = evento.charCode || evento.keyCode;
    var caracter = String.fromCharCode(codigoCaracter);
    // Comprobar si la tecla pulsada es alguna de las teclas especiales
    // (teclas de borrado y flechas horizontales)
    // alert("el caracter : "+caracter+" El codigo : "+codigoCaracter);
    if(caracter=='%' || caracter=="'") return false;

    var tecla_especial = false;
    for(var i in teclas_especiales) {
        if(codigoCaracter == teclas_especiales[i]) {
            tecla_especial = true;
            break;
        }
    }
    // Comprobar si la tecla pulsada se encuentra en los caracteres permitidos
    // o si es una tecla especial
    return permitidos.indexOf(caracter) != -1 || tecla_especial;
}

function soloNumeros(e,c){
    var expl = navigator.appName;
    var value = c.value;

    if(expl== "Microsoft Internet Explorer"){
        if(e.keyCode==46){
            if(value.indexOf(".")==-1){
                return true;
            }
        }
        return ( e.keyCode > 47 && e.keyCode < 58);
    }else{
        var nav4 = window.Event ? true : false;
        var key = nav4 ? e.which : e.keyCode;

        if(key==46){
            if(value.indexOf(".")==-1){
                return true;
            }
        }
        return ( key==8 || (key >= 48 && key <= 57));
    }
}

function validateNumber(event) {
    var key = window.event ? event.keyCode : event.which;
    //  alert(key);
    if ((key >47 && key<58) || key==0 || key==8) {
        return true;
    }
    return false;
}

function focusInCampo(elEvento,campo) {
    // alert('entro a focus in campo');
    var evento = elEvento || window.event;
    var codigoCaracter = evento.charCode || evento.keyCode;
    // var caracter = String.fromCharCode(codigoCaracter);
    //   alert(caracter);
    if(codigoCaracter==9){
        $toma(campo).focus();
        return false;
    }
    return true;
}

function $toma(id){
    //alert('entro a tomar');
    return document.getElementById(id);
}

//funcion para limpiar y cerrar los modal panel de insertar
function cleanAndCloseModal(form,labelError,richMessage,modal){
    // button.form.reset();

    $toma(labelError).innerHTML='';
    $toma(richMessage).style.display='none';
    var miForm=$toma(form);
    
    for (i=0;i<miForm.elements.length;i++)
    {
        if(miForm.elements[i].type=='text'){
            miForm.elements[i].value='';
        }else if(miForm.elements[i].type=='password'){
            miForm.elements[i].value='';
        //    $toma(miForm.elements[i].id).value='';
        }else if(miForm.elements[i].type=='checkbox'){
            miForm.elements[i].checked=false;
        }else if(miForm.elements[i].type=='select-one'){
            miForm.elements[i].selectedIndex=0;
        }
    }
    //   alert('llego el modal es '+modal);
    RichFaces.$(modal).hide()
    // alert('paso');
    return false;
}

function cleanForm(form,labelError,richMessage){
    // button.form.reset();

    if(labelError!=null){
        $toma(labelError).innerHTML='';
    }
    if(richMessage!=null){
        $toma(richMessage).style.display='none';
    }
    var miForm=$toma(form);

    for (i=0;i<miForm.elements.length;i++)
    {
        if(miForm.elements[i].type=='text'){
            miForm.elements[i].value='';
        }else if(miForm.elements[i].type=='textarea'){
            miForm.elements[i].value='';
        //    $toma(miForm.elements[i].id).value='';
        }else if(miForm.elements[i].type=='password'){
            miForm.elements[i].value='';
        //    $toma(miForm.elements[i].id).value='';
        }else if(miForm.elements[i].type=='checkbox'){
            miForm.elements[i].checked=false;
        }else if(miForm.elements[i].type=='select-one'){
            miForm.elements[i].selectedIndex=0;
        }
        else if(miForm.elements[i].type=='radio'){
            miForm.elements[i].checked=false;
        }
    }
//   alert('llego el modal es '+modal);
// alert('paso');
}

/* Funcion que permite cerrar un modal y limpiar su fu formulario si es que quisieramos
   esta funcion debe ser llamada en el evento on beforeShow de la siguiente forma
 */
// var ActiveModal = new function(modal,form,lblError,richMsg){
var ActiveModal = new function(){

    this.activeModalPanel =  null;
    this.miForm =  null;
    this.lblError= null;
    this.richMessage= null;

    this.setActiveModalPanel = function(a) {
        //console.debug('el modal seteado es',a);
        this.activeModalPanel = a;
    }

    this.setMiForm = function(a) {
        //   console.debug('el form seteado es',a);
        this.miForm = a;
    }

    this.setLblError=function(a){
        //  console.debug('el lbl seteado es',a);
        this.lblError=a;
    }

    this.setRichMessage=function(a){
        // console.debug('el id del richmsg seteado es',a);
        this.richMessage=a;
    }

    this.cleanAndClose=function(){
        //  console.debug('entro a clean and close');
        if(this.miForm==null || this.miForm=='undefined'){

            Richfaces.hideModalPanel(this.activeModalPanel);
            return;
        }

        this.miForm=$toma(this.miForm);
        this.lblError=$toma(this.lblError);
        this.richMessage=$toma(this.richMessage);

        for (i=0;i<this.miForm.elements.length;i++)
        {
            if(this.miForm.elements[i].type=='text'){
                this.miForm.elements[i].value='';
            }else if(this.miForm.elements[i].type=='password'){
                //   miForm.elements[i].value='';
                this.miForm.elements[i].value='';
            }else if(this.miForm.elements[i].type=='checkbox'){
                this.miForm.elements[i].checked=false;
            }else if(this.miForm.elements[i].type=='select-one'){
                this.miForm.elements[i].selectedIndex=0;
            }
        }
        this.lblError.innerHTML='';
        this.richMessage.style.display='none';
        
        Richfaces.hideModalPanel(this.activeModalPanel);
        this.activeModalPanel =  null;
        this.miForm =  null;
        this.lblError= null;
        this.richMessage= null;
    }
};


function resetForm(form,labelError,richMsg){
    miForm=form;
    labelError=$toma(labelError);
    if(richMsg!=null){
        richMsg=$toma(richMsg);
    }
    var miForm=document.getElementById('frmLogin');
    for(i=0;i<miForm.elements.length;i++){
        if(miForm.elements[i].type=="text" || miForm.elements[i].type=="password"){
            miForm.elements[i].value="";
        }
    }
    labelError.innerHTML='';
    if(richMsg!=null){
        richMsg.style.display='none';
    }
}

function fixTabIndexes(elem) {      
    //See rich faces bug https://issues.jboss.org/browse/RF-10980 (tab index set to -1 in popup panel)
    $(elem).find('*[tabindex="-1"]').each(function() {
        $(this).removeAttr('tabindex');
    });
}

function showModalPanel(modal){ 
    RichFaces.$(modal).show();

    var windowWidth = parseInt(document.documentElement.clientWidth);
    var windowHeight = parseInt(document.documentElement.clientHeight);
    //console.dir($toma(modal));
    var popupHeight = RichFaces.$(modal).height();
    var popupWidth = RichFaces.$(modal).width();

    var top = Math.round(windowHeight/2-popupHeight/2);
    var left = Math.round(windowWidth/2-popupWidth/2);

    RichFaces.$(modal).setLeft(left);
    RichFaces.$(modal).setTop(top);
    
    fixTabIndexes($("[id='"+modal+"_content']"));

}

function validateDate(dateFrom,dateTo){
    dateFrom=$('[id="'+dateFrom+'"]').val();
    dateTo=$('[id="'+dateTo+'"]').val();
    dateFrom=new Date(dateFrom.toString().substring(6, 10), dateFrom.toString().substring(3, 5), dateFrom.toString().substring(0, 2));
    dateTo=new Date(dateTo.toString().substr(6, 4), dateTo.toString().substr(3, 2), dateTo.toString().substr(0, 2));

    if(dateFrom.getTime()>dateTo.getTime()){
        return false;
    }
    return true;
}
