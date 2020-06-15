package com.APIProject.apiProject.Model;

import com.APIProject.apiProject.domain.business.GuerrillaUsuario;
import com.APIProject.apiProject.domain.business.TipoUnidad;
import com.APIProject.apiProject.domain.business.UnidadBatalla;
import com.APIProject.apiProject.service.GuerrillaService;
import com.APIProject.apiProject.service.Tipo_UnidadService;
import com.APIProject.apiProject.service.UnidadBatallaService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class StolenResources {

    @Autowired
    GuerrillaService guerrillaService = new GuerrillaService();

    @Autowired
    UnidadBatallaService unidadBatallaService = new UnidadBatallaService();

    @Autowired
    Tipo_UnidadService tipoUnidadService = new Tipo_UnidadService();

    DecideRanking decideRanking;

    //Permite calcular el total de recursos que se le pueden robar al enemigo
    public void StolenResources(GuerrillaUsuario atacante, GuerrillaUsuario atacado){
        //Declaracion de Variables
        Float pillajeTotal;
        Float pillajePetroleo;
        Float pillajeDinero;

        UnidadBatalla unidad = atacante.getUnidad_batallas().get(0);
        List<TipoUnidad> unidades = unidad.getUnidadesBatalla();

        for(TipoUnidad tipoUnidad: unidades){
            // Se calcula el valor de los datos del pillaje
            pillajeTotal = tipoUnidad.getTotal_unidades()*tipoUnidad.getPillaje();
            pillajePetroleo = pillajeTotal * (60/100);
            pillajeDinero= pillajeTotal * (40/100);

            // Se confirma que tenga recursos que robar
            if(atacado.getDinero()!=0 || atacado.getPetroleo()!=0){
                Float newPijalleDinero = pillajeDinero;
                Float newPijallePetroleo = pillajePetroleo;
                pillajeDinero=pillajeDinero-atacado.getDinero();
                pillajePetroleo=pillajePetroleo-atacado.getPetroleo();
                if(pillajeDinero<=0 && pillajePetroleo<=0){//Tiene suficiente Oro y Petroleo
                    atacante.setDinero(atacante.getDinero()+newPijalleDinero);
                    atacado.setDinero(atacado.getDinero()-newPijalleDinero);
                    atacante.setPetroleo(atacante.getPetroleo()+newPijallePetroleo);
                    atacado.setPetroleo(atacado.getPetroleo()-newPijallePetroleo);
                }else if(pillajeDinero>0 && pillajePetroleo<=0){//No tiene suficiente Oro pero petroleo si
                    atacante.setDinero(atacante.getDinero()+atacado.getDinero());
                    atacado.setDinero(0.0f);
                    pillajePetroleo=0.0f;
                    pillajePetroleo= newPijallePetroleo+pillajeDinero;
                    newPijallePetroleo=pillajePetroleo;
                    pillajePetroleo=pillajePetroleo-atacado.getPetroleo();
                    if(pillajePetroleo<0){
                        atacante.setPetroleo(atacante.getPetroleo()+newPijallePetroleo);
                        atacado.setPetroleo(atacado.getPetroleo()-newPijallePetroleo);
                    }else{
                        atacante.setPetroleo(atacante.getPetroleo()+atacado.getPetroleo());
                        atacado.setPetroleo(0.0f);
                    }
                }else if(pillajeDinero<=0 && pillajePetroleo>0){//No tiene suficiente Petroleo pero Oro si
                    atacante.setPetroleo(atacante.getPetroleo()+atacado.getPetroleo());
                    atacado.setPetroleo(0.0f);
                    pillajeDinero=0.0f;
                    pillajeDinero= newPijalleDinero+pillajePetroleo;
                    newPijalleDinero=pillajeDinero;
                    pillajeDinero=pillajeDinero-atacado.getDinero();
                    if(pillajeDinero<0){
                        atacante.setDinero(atacante.getDinero()+newPijalleDinero);
                        atacado.setDinero(atacado.getDinero()-newPijalleDinero);
                    }else{
                        atacante.setDinero(atacante.getDinero()+atacado.getDinero());
                        atacado.setDinero(0.0f);
                    }
                }else{//No tienen suficientes recursos
                    atacante.setDinero(atacante.getDinero()+atacado.getDinero());
                    atacado.setDinero(0.0f);
                    atacante.setPetroleo(atacante.getPetroleo()+atacado.getPetroleo());
                    atacado.setPetroleo(0.0f);
                }
            }
        }
        decideRanking = new DecideRanking();
        decideRanking.DecideRanking(atacante);
        decideRanking.DecideRanking(atacante);

        //Se guardan los cambios
        guerrillaService.update(atacante);
        guerrillaService.update(atacado);
    }
}
