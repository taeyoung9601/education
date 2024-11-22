import { useEffect, useState } from 'react';
import styles from './About.module.css';


console.groupCollapsed('src/component/About.js'); console.groupEnd();

const About = (props) => {
    console.groupCollapsed('About(',props,') invoked.'); console.groupEnd();

    return (
        <div className={styles.container}>
            <h2 className={styles.h2}>About Us</h2>

            <p className={styles.paragraph}>
                aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa.a
                asddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd
                zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz
                asssssssssssssssssssssssssssssssssssssssssssssssssssssssss
            </p>
        </div>
    );
};

export default About;
