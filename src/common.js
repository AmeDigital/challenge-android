import React, { Component } from 'react';
import { View, Text, ScrollView, TouchableOpacity, Platform, ImageBackground, Dimensions, Image } from 'react-native';
import { Icon } from 'react-native-elements';

import Styles, {primaryColor, BannerWidth, } from '../Styles';
const { style  } = Styles;

export class Title extends Component{
    render(){
        return(
            <Text style={style.title}>{this.props.children}</Text>
        )
    }
}
export class FlexRow extends Component{
    render(){
        return(
            <View style={[this.props.style, {flexDirection: 'row'}]}>
                {this.props.children}
            </View>
        )
    }
}
export class Row extends Component{
    render(){
        return(
            <View style={[this.props.style, style.row]}>
                {this.props.children}
            </View>
        )
    }
}
export const mainHeader = (navigation) => {

    return {
        headerTitle:
        <View style={{alignItems: 'center',}}>
            <Image source={require('./assets/images/logo.png')} style={{width:180, height: 40, marginLeft: 20,}}/>
        </View>,
        headerLeft: (
            <TouchableOpacity style={{marginLeft: 20}}  onPress={()=>{ navigation.toggleDrawer()}}>
               <Icon  name='menu' color='#FFF' size={35} /> 
            </TouchableOpacity>
          ),   
        headerStyle: {
            elevation: 0,
            height: 70,
            borderBottomWidth: 0,
            backgroundColor: primaryColor
        }
    }
}