import React, { Component } from 'react';
import { View, Text , ScrollView , Image , TouchableOpacity , Alert} from 'react-native';
import { Icon } from 'react-native-elements';
import EventEmitter from "react-native-eventemitter";

import Animation from 'lottie-react-native';
import anim from '../assets/json/loader.json';

import API from '../core/API';

import {  Row , Title, FlexRow } from '../common';

import Styles, { greyMedium , DeviceHeight , primaryColor} from '../../Styles';
const { style } = Styles;

class ProductScreen extends Component {

    static navigationOptions = ({navigation}) => {
        return ({
        headerStyle: {
            backgroundColor: 'transparent',
            marginBottom: -100, 
            borderBottomWidth: 0 , 
            elevation: 0
        },
      })
    }; 

    constructor(props){
        super(props);
        let {navigation} = props
        this.state = { 
            item: navigation.getParam('item')
        }
    }

    async componentDidMount(){
        let produto =  await API.getProduct(this.state.item.id)
        this.setState({produto})
    }
    async reservarProduto(){
        let reservar = await API.reservarProduto(this.state.item.id)
        if(reservar.result == 'success'){
            Alert.alert(
                'Produto Reservado','',
                [{text: 'OK', onPress: () => this.props.navigation.pop()}]);              
        }else{
            Alert.alert(
                'Erro ao reservar produto','',
                [{text: 'OK', onPress: () => this.props.navigation.pop()}]);
        }
    }
        
        

    renderButonReservar(){
        let middle = DeviceHeight / 2 + 150
        return(
            <TouchableOpacity onPress={()=>{this.reservarProduto()}} style={{position:'absolute', right:30, top:middle, zIndex:99999,justifyContent:'center', alignItems:'center'}}>
                <View style={{width:80, height: 80, borderRadius: 50, backgroundColor: primaryColor ,justifyContent:'center', alignItems:'center' }}>
                    <Icon name='ios-checkmark' color='#FFF' type={'ionicon'} size={60}/>
                </View>
            </TouchableOpacity>
        )
    }
    loading(){
            return(
                <View style={{position: 'absolute',
                top:0, bottom:0, left:0, right:0,
                justifyContent: 'center', alignItems: 'center',
                zIndex: 999,}} >
                    <Animation
                        ref={animation => {
                        this.animation = animation;
                        }}
                        style={{
                        width: 80,
                        height: 80,
                        }}
                        loop={true}
                        source={anim}
                    />
                </View>
            )
    }
    
    renderProduto(){
        let { produto } = this.state
        if(produto){
            return(
               <View>
                    {this.renderButonReservar()}
                    <ScrollView>
                        <View style={{justifyContent: 'center', alignItems: 'center', marginTop: 30,}}>
                            <Image style={{width:150, height: 180}} source={{uri: produto.urlImagem}} />
                        </View>
                        <View style={{marginTop: 20, marginLeft: 20,}}>
                            <Title>{produto.nome}</Title>
                        </View>
                        <View style={style.priceProducto}>
                            <FlexRow style={{ justifyContent: 'space-between', alignItems: 'center',marginHorizontal: 30, padding: 5,}}>
                                <Text style={{textDecorationLine:'line-through' , color:greyMedium, fontSize: 15}}>De R$ {produto.precoDe}</Text>
                                <Text style={{fontSize:23 , color:'#f15025' , fontWeight: 'bold'}} >Por R$ {produto.precoPor}</Text>
                            </FlexRow>
                        </View>
                        <View style={{marginTop: 15,}}>
                            <Title>Descrição</Title>
                        </View>
                        <View style={{marginTop: 20, marginHorizontal: 25,}}>
                            <Text>{produto.descricao}</Text>
                        </View>
                        <Row />
                        <Row />
                        <Row />

                    </ScrollView>

               </View> 
            )
        }
    }
    
    render(){
        return(
            <View>
                {this.renderProduto()}
            </View>
        )
    }

}

export default ProductScreen;