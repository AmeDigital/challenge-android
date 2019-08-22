import React, { Component } from 'react';
import { View, Text , ScrollView } from 'react-native';
import ProductList from '../components/ProductList'
import Animation from 'lottie-react-native';
import anim from '../assets/json/loader.json';

import API from '../core/API';

import { 
    Title
} from '../common';

import Styles, { primaryColor } from '../../Styles';
const { style } = Styles;

class CategoryScreen extends Component {

    static navigationOptions = ({navigation}) => {
        return ({
        title: navigation.getParam('item').descricao,
        headerStyle: {
          backgroundColor: primaryColor,
        },
        headerTintColor: '#fff',
        headerTitleStyle: {
          fontWeight: 'bold',
        },
      })
    }; 

constructor(props){
    super(props);

    this.state = {
        name: props.navigation.getParam('item').descricao,
        category: props.navigation.getParam('item'),
        hasMore: true,
        loading:false,
        pagina: 1,
    }
    console.log('state => ', this.state.category)
}

handleView = (type, item) => {
    switch(type){
        case 'category':
        return this.props.navigation.navigate('category', item)
        case 'product':
        return this.props.navigation.navigate('product', item)
    }
}

async componentDidMount(){
    let productList =  await API.getProductsList(this.state.page, this.state.category.id)
    this.setState({productList})    
}

loading(){
        return(
            <View style={{height:150 ,justifyContent: 'center', alignItems: 'center' , }} >
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

async loadMore(){
    if(this.state.hasMore && !this.state.loading){
        await this.setState({loading: true})
        this.animation.play();
        let pagina = this.state.pagina + 1;
        let productsList = await API.getProductsList(pagina, this.state.category.id)
        if(productsList.length == 0){
            await this.setState({hasMore : false})
            this.setState({loading: false})
        }else{
            let productsItems = this.state.productList
            productsItems = productsItems.concat(productsList)
            await this.setState({
                pagina,
                productList: productsItems
            })
            this.setState({loading: false})
        }

    }
}

isCloseToBottom({ layoutMeasurement, contentOffset, contentSize }) {   
    return layoutMeasurement.height + contentOffset.y >= contentSize.height - 30; 
}

    render(){
        return(
            <ScrollView 
            onScroll={({ nativeEvent }) => {
                if (this.state.productList  && this.isCloseToBottom(nativeEvent)) {                
                     this.loadMore(); }}}>
                        <ProductList products={this.state.productList} handleView={this.handleView}/>
                        {this.state.loading && this.loading()}
            </ScrollView>
        )
    }
}

export default CategoryScreen;