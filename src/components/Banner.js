import React, { Component } from 'react';
import { View, Text , TouchableWithoutFeedback , Image , Linking } from 'react-native';

import Carousel from 'react-native-banner-carousel';

import Styles, {primaryColor, BannerWidth, } from '../../Styles';
const { style  } = Styles;


class Banner extends Component{

    renderSlide(slide, index){
        return (
            <View key={index}>
                <TouchableWithoutFeedback onPress={ ()=> Linking.openURL('https://www.submarino.com.br/')} >
                <View>
                    <Image style={{ width: BannerWidth, height: '100%' }} source={{uri: slide.urlImagem}}  resizeMode={'cover'}/>
                </View>
                </TouchableWithoutFeedback>
            </View>
        );
    }

    getCarousel(){
        if(this.props.banners && this.props.banners.length){
            return(
                <Carousel
                    autoplay
                    autoplayTimeout={15000}
                    loop
                    index={0}
                    pageSize={BannerWidth}
                >
                    {this.props.banners.map((slide, index) =>  this.renderSlide(slide, index))}
                </Carousel>
            )
        }
    }

    render(){
        return(
            <View style={style.mainBanner} >
                {this.getCarousel()}
            </View>
        )
    }
}

export default Banner;
