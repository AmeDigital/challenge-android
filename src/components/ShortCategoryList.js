import React, { Component } from 'react';
import { View, Text, TouchableOpacity, Image , ScrollView} from 'react-native';

import { 
    Title, FlexRow,
} from '../common';

import Styles, { grey } from '../../Styles';
const { style } = Styles;


class ShortCategoryList extends Component{ 

    renderCategorias(){
        if(this.props.categorias && this.props.categorias.length){
            let { categorias } = this.props
            let categoriaslist = categorias.reverse().map((categoria)=>{
                return(
                    <TouchableOpacity style={{width:90,marginTop:20, marginHorizontal:10 }} key={categoria.id} onPress={() => {this.props.handleView('category', {item: categoria})}}>
                        <View style={{justifyContent:'center' , alignItems: 'center'}} >
                            <Image style={{ width:90, height: 90 }} source={{uri: categoria.urlImagem}} />
                            <Text style={{textAlign:'center', marginTop: 5}}>{categoria.descricao}</Text>
                        </View>
                    </TouchableOpacity >
                )
            })
            return categoriaslist
        }
    }
    
 render(){
     return(
         <View >
            <ScrollView horizontal>
                {this.renderCategorias()}
            </ScrollView>
         </View>
     )
 }
 
}
export default ShortCategoryList;