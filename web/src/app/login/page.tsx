"use client"

import { useEffect, useState } from "react"
import Link from "next/link"
import { EyeIcon, EyeSlashIcon } from "@heroicons/react/24/solid"

const Page = () => {

    const [username, setUsername] = useState(''); 
    const [password, setPassword] = useState(''); 
    const [isEye, setIsEye] = useState(true);
    const [isValidated, setIsValidated] = useState(false); 

    const handleEye = () => {
        setIsEye(!isEye);
    }

    useEffect(() => {
        const regex = /^(?!.*\s)(?=.*[^A-Za-z0-9]).+$/;
        if (username.length > 5 && password.length > 5 && regex.test(password)
        ) {
            setIsValidated(true);
        } else {
            setIsValidated(false)
        }
    }, [username, password]);


    return (
        <main className="grid grid-cols-2">
            <div></div> 
            <div className="flex flex-col items-center justify-between h-screen p-3">
                <div></div>
                <div className="h-1/2 w-2/3 flex flex-col">
                    <h1 className="text-center text-4xl">Log in</h1>
                    <form action="#" className="flex flex-col grow justify-around items-center" name="username">
                        <input 
                            type="text" 
                            placeholder="Enter username" 
                            className=" p-3 rounded-3xl w-3/4" 
                            value={username} 
                            onChange={(e) => setUsername(e.target.value)} 
                            required
                        />
                        <div className="relative w-3/4">
                            <input
                                type={isEye ? "password" : "text"}
                                placeholder="Enter password"
                                className="p-3 pr-10 rounded-3xl w-full"
                                value={password}
                                onChange={(e) => setPassword(e.target.value)}
                                required
                            />
                            <div className="absolute right-2 top-1/2 transform -translate-y-1/2 cursor-pointer text-gray-500">
                                {isEye ? (
                                <EyeIcon className="h-4 w-4" onClick={handleEye} />
                                ) : (
                                <EyeSlashIcon className="h-4 w-4" onClick={handleEye} />
                                )}
                            </div>
                        </div>
                        <button 
                            type="submit"
                            className={`${isValidated ? "bg-black cursor-pointer" : "bg-gray-500 cursor-not-allowed"} p-3 rounded-3xl text-gray-100 w-3/4`}
                            disabled={!isValidated}
                            >
                            Log in
                        </button>
                    </form>
                </div>
                <div className="flex">
                    <div className="text-sm p-3 text-gray-500">Don't have an account?</div>
                    <Link href="/register" passHref>
                        <button type="button" className="bg-black py-3 px-5 rounded-3xl text-sm text-gray-100 cursor-pointer">Sign up</button>
                    </Link>
                </div>
            </div>
        </main>
    )
}

export default Page