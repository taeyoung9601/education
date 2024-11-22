import {Component} from 'react';
import {Route, Routes,Link,} from 'react-router-dom';

import styles from './YTS.module.css';
import {Detail, Home, About} from '.'         // 궁금 . 으로 하는거

console.groupCollapsed('src/component/YTS.js'); console.groupEnd();


class YTS extends Component {


    render(){
        console.groupCollapsed('render() invoked.'); console.groupEnd();

        return (
            <div>
                <nav>
                    <p className={styles.paragraph}>
                    <Link to={'/'}>Home</Link>&nbsp;&nbsp;
                    <Link to={'/about'}>About</Link>
                    </p>
                </nav>
                <hr/>
                <Routes>
                    <Route path='/' element={<Home/>} caseSensitive={true}/>
                    <Route path='/about' Component={About} caseSensitive={true}/>
                    <Route path='/movie/:id' element={<Detail/>} caseSensitive={true}/>
                </Routes>
            </div>
        );
    }

}


export default YTS;