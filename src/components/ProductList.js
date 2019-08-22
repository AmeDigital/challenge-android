import React, { Component } from 'react';
import { View, Text, TouchableOpacity, Image , ScrollView} from 'react-native';
import { Icon } from 'react-native-elements';
import LottieView from 'lottie-react-native';
import semProduto from '../assets/json/semProduto.json';
import anim from '../assets/json/loader.json';


import { FlexRow, Title , Row } from '../common';

import Styles, { grey ,  greyMedium , primaryColor , minimumDisplay , DeviceWidth} from '../../Styles';
const { style } = Styles;


class ProductList extends Component{

    constructor(props){
        super(props);
        this.state = {
            loading: true
        }
    }

    renderProducts(){
        let { products } = this.props

        if(products && products.length){
            if(this.state.loading) this.setState({loading: false})
            let list = products.map((item) => {
                return(
                    <TouchableOpacity style={{marginTop: 20, marginHorizontal: 20, borderBottomWidth: 1, borderBottomColor:grey, }} key={item.id} onPress={() => {this.props.handleView('product', {item: item})}}>
                        <FlexRow  style={{paddingBottom: 15}}>
                            <Image style={{width:90, height: 100}} source={{uri: item.urlImagem}} />
                            <View style={{flex:1, marginLeft: 10,}}>
                                <Text style={{  marginTop: 15 , fontSize:20 , fontWeight: 'bold',}}>{item.nome}</Text>
                                <FlexRow style={{ justifyContent: 'space-between', alignItems: 'center', marginTop: 20, paddingRight: 10}}>
                                    <Text style={{textDecorationLine:'line-through' , color:greyMedium, fontSize: DeviceWidth <= minimumDisplay ? 12 : 16}}>De R$ {item.precoDe}</Text>
                                    <Text style={{fontSize: DeviceWidth <= minimumDisplay ? 13 : 23 , color:'#f15025' , fontWeight: 'bold'}} >Por R$ {item.precoPor}</Text>
                                </FlexRow>
                            </View>
                            <View style={{justifyContent:'center', paddingRight: 8,}}>
                                <Icon  name='ios-arrow-forward' type='ionicon' color={primaryColor} size={25} />
                            </View>
                        </FlexRow>
                        
                    </TouchableOpacity >
                )
            })
            return(
                <View>
                    {!!this.props.title && <View style={{marginTop: 30, borderBottomWidth: 1, borderBottomColor: grey}}>
                    <Title>{this.props.title}</Title>
                </View>}
                    {list}
                </View>
            )
        }else if(products === undefined){
            if(this.animation) this.animation.play()
        }else if(products.length == 0){
            if(this.state.loading) this.setState({loading: false})
            return this.produtoNaoEncontrado()
        }
    }
    produtoNaoEncontrado(){
        return(
            <View style={{flex: 1 , justifyContent: 'center', alignItems: 'center', }} >
                <LottieView
                    style={{
                    width: 400,
                    height: 400,
                    }}
                    autoPlay
                    loop
                    source={semProduto}
                />
            <Text>Produtos não encontrados</Text>
            </View>
        )
    }
    loading(){
        if(this.state.loading){
            return(
                <View style={{position: 'absolute',
                top:0, bottom:0, left:0, right:0,
                justifyContent: 'center', alignItems: 'center',
                zIndex: 999,}} >
                    <LottieView
                        ref={animation => {
                        this.animation = animation;
                        }}
                        style={{
                        width: 80,
                        height: 80,
                        }}
                        loop
                        autoPlay
                        source={anim}
                    />
                </View>
            )
        }
    }
    
 render(){
     return(
         <View >
            <ScrollView >
                {this.loading()}
                {this.renderProducts()}
            </ScrollView>
         </View>
     )
 }
 
}
export default ProductList;