import React, { Component } from 'react';
import { View, Text , ScrollView  } from 'react-native';
import LottieView from 'lottie-react-native';
import anim from '../assets/json/loader.json';

import API from '../core/API';

import Banner from '../components/Banner'
import ShortCategoryList from '../components/ShortCategoryList'
import ProductList from '../components/ProductList'

import {  mainHeader , Title } from '../common';

import Styles, { DeviceWidth , DeviceHeight , grey } from '../../Styles';
const { style } = Styles;

class HomeScreen extends Component {

    static navigationOptions = ({navigation}) => (mainHeader(navigation, true));
    
    constructor(props){
        super(props);

        this.state = { 
            banners: null,
        }
    }

async componentDidMount(){
    
    let banners =  await API.getBanner()
    let categorias =  await API.getCategory()
    let maisVendidos =  await API.getMaisVendidos()
    this.setState({banners , categorias , maisVendidos})
}

loading(){
    if(this.state.maisVendidos == undefined && this.state.categorias == undefined){
        return(
            <View style={{position: 'absolute',
            top:0, bottom:0, left:0, right:0,
            justifyContent: 'center', alignItems: 'center',
            zIndex: 999,}} >
                <LottieView
                    style={{
                    width: 100,
                    height: 100,
                    }}
                    loop
                    autoPlay
                    source={anim}
                />
            </View>
        )
    }
}
handleView = (type, item) => {
    switch(type){
        case 'category':
        return this.props.navigation.navigate('category', item)
        case 'product':
        return this.props.navigation.navigate('product', item)
    }
}

render(){
    return(
        <ScrollView style={{flex:1}}>
            {this.loading()}
            <View>
                <Banner banners={this.state.banners} />
            </View>
            <View>
                <View style={{marginTop: 25, borderBottomWidth: 1, borderBottomColor: grey}}>
                    <Title>Categorias</Title>
                </View>
                <ShortCategoryList categorias={this.state.categorias} handleView={this.handleView} />
            </View>
            <View>
                <ProductList title='Mais vendidos' products={this.state.maisVendidos} handleView={this.handleView}/>
            </View>

        </ScrollView>
    )
    }

}

export default HomeScreen;