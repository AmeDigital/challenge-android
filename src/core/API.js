import {Platform, AsyncStorage} from 'react-native';
import EventEmitter from "react-native-eventemitter";
import axios from 'axios'; 


const urlBase= 'https://alodjinha.herokuapp.com'


export const validateConsult = async (consult, sucessCallBack, unauthorizationCallback) => {
    if(consult.status >= 200 && consult.status <= 299){
        return sucessCallBack();
    } else if( consult.status >= 400 && consult.status <= 499){
        // 401 retornar para autorização
        console.log('error 400+ ', JSON.stringify(consult) )
        return unauthorizationCallback();
    } else {
        console.log('error 300+/500+ ', JSON.stringify(consult) )
        EventEmitter.emit('toastMessage', 'Estamos com problemas para conectar com o servidor')
        return undefined
    }
}

export default{

     async getBanner(){
         try{
             let url = `${urlBase}/banner`;

             let consult = await axios.get(url)
             .then(response => response)
             .catch(err => err.response);
             let consultValidated = await validateConsult(
                 consult,
                 () =>{
                     consult.data.success = true
                     return consult.data.data;
                 }, 
                 () => {
                     consult.data.success = false
                     console.log('erro 400+ ', JSON.stringify(consult) )
                     return cconsult.data.data;
                 }
             )
             return consultValidated;
         } catch(err){
         console.log('error: ', err);
         }  
     },
     async getCategory(){
        try{
            let url = `${urlBase}/categoria`;

            let consult = await axios.get(url)
            .then(response => response)
            .catch(err => err.response);
            let consultValidated = await validateConsult(
                consult,
                () =>{
                    consult.data.success = true
                    return consult.data.data;
                }, 
                () => {
                    consult.data.success = false
                    console.log('erro 400+ ', JSON.stringify(consult) )
                    return consult.data.data;
                }
            )
            return consultValidated;
        } catch(err){
        console.log('error: ', err);
        }  
    },
    async getMaisVendidos(){
        try{
            let url = `${urlBase}/produto/maisvendidos`;

            let consult = await axios.get(url)
            .then(response => response)
            .catch(err => err.response);
            let consultValidated = await validateConsult(
                consult,
                () =>{
                    consult.data.success = true
                    return consult.data.data;
                }, 
                () => {
                    consult.data.success = false
                    console.log('erro 400+ ', JSON.stringify(consult) )
                    return consult.data.data;
                }
            )
            return consultValidated;
        } catch(err){
        console.log('error: ', err);
        }  
    },
    async getProductsList(page = 1, category_id){

        try{
            let offset = (page-1)*20
            let url = `${urlBase}/produto?offset=${offset}&limit=${offset + 20}${category_id ? '&categoriaId='+category_id: ''}`;
            let consult = await axios.get(url)
            .then(response => response)
            .catch(err => err.response);
            let consultValidated = await validateConsult(
                consult,
                () =>{
                    consult.data.success = true
                    return consult.data.data;
                }, 
                () => {
                    consult.data.success = false
                    console.log('erro 400+ ', JSON.stringify(consult) )
                    return consult.data.data;
                }
            )
            return consultValidated;
        } catch(err){
        console.log('error: ', err);
        }  
    },
    async getProduct(id){
        try{
            let url = `${urlBase}/produto/${id}`;

            let consult = await axios.get(url)
            .then(response => response)
            .catch(err => err.response);
            let consultValidated = await validateConsult(
                consult,
                () =>{
                    consult.data.success = true
                    return consult.data;
                }, 
                () => {
                    consult.data.success = false
                    console.log('erro 400+ ', JSON.stringify(consult) )
                    return consult.data;
                }
            )
            return consultValidated;
        } catch(err){
        console.log('error: ', err);
        }  
    },
    async reservarProduto(id){
        try{
            let url = `${urlBase}/produto/${id}`;
            let consult = await axios.post(url)
            .then(response => response)
            .catch(err => err.response);
            let consultValidated = await validateConsult(
                consult,
                () =>{
                    consult.data.success = true
                    return consult.data;
                }, 
                () => {
                    consult.data.success = false
                    console.log('erro 400+ ', JSON.stringify(consult) )
                    return consult.data;
                }
            )
            return consultValidated;

        } catch(err){
        console.log('error: ', err);
        }  
        console.log('consultValidated' , consultValidated)
    },

 }


